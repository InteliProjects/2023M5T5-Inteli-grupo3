import * as d3 from 'd3'; // Importa a biblioteca D3.js.

// Interface para representar um nó.
interface Node {
  id: string;
  type: string;
  position: { x: number; y: number };
  data: { label: string };
}

// Interface para representar uma rota.
interface Route {
  id: string;
  source: string;
  target: string;
  label: string;
  type: string;
  style: { strokeWidth: number; stroke: string };
}

// Função que obtém informações sobre válvulas a partir de uma API.
export const getValves = async (link: any) => {
  const response = await fetch(`http://127.0.0.1:8080/api/projects/name=${link}/nodes`);
  const json = await response.json();

  // Mapeia os dados obtidos para o formato desejado.
  const valves = json.map((i: any) => ({
    id: `${i.id}`,
    type: `${i.labels[0]}`,
    data: { label: i.properties.name },
  }));

  // Cria uma simulação de forças usando a biblioteca D3.js.
  const simulation = d3.forceSimulation(valves)
    .force('charge', d3.forceManyBody().strength(-2000)) // Força de repulsão
    .force('link', d3.forceLink([]).id((d: any) => d.id).distance(100)) // Força de ligação
    .force('center', d3.forceCenter(1000, 900)) // Posição central

  // Executa a simulação.
  simulation.tick(300);

  // Mapeia os resultados da simulação de volta para o formato desejado.
  const out: Node[] = valves.map((d: any) => ({
    ...d,
    position: {
      x: d.x,
      y: d.y,
    },
  }));

  return out;
};

export const getRoutes = async (link: any) => {
  const response = await fetch(`http://127.0.0.1:8080/api/projects/name=${link}/paths`);
  const json = await response.json();

  // Usar um conjunto para armazenar as conexões únicas, representadas como strings
  const uniqueConnections = new Set();

  // Mapeia os dados obtidos para o formato desejado.
  const ids: Route[] = json.map((i: any) => {
    let cor = "";

    // Atribui cores com base no tipo de rota.
    switch (i.type) {
      case "filtration 1":
        cor = "#2D20B3";
        break;
      case "filtration 2":
        cor = "#A37BE9";
        break;
      case "green beer out 1":
        cor = "#32B320";
        break;
      case "green beer out 2":
        cor = "#5C8057";
        break;
      case "wort 1":
        cor = "#F5E510";
        break;
      case "wort 2 - future":
        cor = "#FCBE21";
        break;
      default:
        cor = "pink";
    }

    // Ordene os nós de origem e destino para garantir consistência
    const sortedNodes = [i.sourceId, i.targetId].sort();
    const connection = `${sortedNodes[0]}-${sortedNodes[1]}`;

    if (!uniqueConnections.has(connection)) {
      uniqueConnections.add(connection);

      return {
        id: `${i.id}`,
        source: `${sortedNodes[0]}`,
        target: `${sortedNodes[1]}`,
        label: i.type,
        type: "step",
        style: { strokeWidth: 5, stroke: cor },
      };
    } else {
      // Se for uma conexão duplicada, retorne null ou um valor vazio
      return null
    }
  }).filter((item: any) => item !== null)
  return ids
};

// Função que obtém informações sobre caminhos a partir de uma API.
export const getCaminho = async ({ start, goal, restriction1, restriction2 }: any) => {
  try {
    const response = await fetch(`http://127.0.0.1:8080/api/paths/Dijkstra?begin=${start}&end=${goal}&restriction1=${restriction1}&restriction2=${restriction2}`);
    const json = await response.json();
    // Mapeia os dados obtidos para o formato desejado.
    const mCaminhoP = await Promise.all(json.map(async (i: any) => {

      try {
        const inouts = await fetch(`http://127.0.0.1:8080/api/inouts/id=${i}`);
        if (inouts.ok) {
          return inouts.json();
        }
      } catch (error) {
        console.error("Erro ao buscar inouts para o ID", i);
      }

      try {
        const valvula = await fetch(`http://127.0.0.1:8080/api/valves/id=${i}`);
        if (valvula.ok) {
          return valvula.json();
        }
      } catch (error) {
        console.error("Erro ao buscar valvula para o ID", i);
      }

      try {
        const tanks = await fetch(`http://127.0.0.1:8080/api/tanks/id=${i}`);
        if (tanks.ok) {
          return tanks.json();
        }
      } catch (error) {
        console.error("Erro ao buscar tanks para o ID", i);
      }

      return null; // Retorne null se nenhum recurso for encontrado
    }));


    // Mapeia os resultados para o formato desejado.

    const out: any = mCaminhoP.map((i: any) => ({
      id: `${i.id}`,
      type: `${i.type}`,
      data: { label: `${i.name}` }
    }));



    // Cria uma simulação de forças usando a biblioteca D3.js.
    const simulation = d3.forceSimulation(out)
      .force('charge', d3.forceManyBody().strength(-2000)) // Força de repulsão
      .force('link', d3.forceLink([]).id((d: any) => d.id).distance(100)) // Força de ligação
      .force('center', d3.forceCenter(1000, 900)) // Posição central

    // Executa a simulação.
    simulation.tick(300);
    let a = 0
    // Mapeia os resultados da simulação de volta para o formato desejado.
    const saida: any = out.map((d: any) => ({
      ...d,
      position: {
        x: a = a + 250,
        y: 500
      }
    }));
    return saida;
  } catch (error) {
  }
};

// Função para fazer upload de um tanque para o servidor.
export const uploadTank = async (tank: any, name: any) => {
  // Verifica se um tanque foi fornecido.
  if (tank) {
    const formData = new FormData();
    formData.append('file', tank);
    formData.append('projectName', name);

    // Faz uma solicitação para enviar o tanque para o servidor.
    await fetch('http://127.0.0.1:8080/api/csv/uploadTanque', {
      method: 'POST',
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response;
        }
        throw new Error('Erro ao enviar arquivo.');
      })
      .then((data) => {
        console.log('Resposta do servidor:', data);

      })
      .catch((error) => {
        console.error('Erro:', error);
      });
  } else {
    console.error('Nenhum arquivo selecionado.');
  }
};

// Função para fazer upload de uma válvula para o servidor.
export const uploadValve = async (valve: any, name: any) => {
  // Verifica se uma válvula foi fornecida.
  if (valve) {
    const formData = new FormData();
    formData.append('file', valve);
    formData.append('projectName', name);

    // Faz uma solicitação para enviar a válvula para o servidor.
    await fetch('http://127.0.0.1:8080/api/csv/uploadValvula', {
      method: 'POST',
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response;
        }
        throw new Error('Erro ao enviar arquivo.');
      })
      .then((data) => {
        console.log('Resposta do servidor:', data);

      })
      .catch((error) => {
        console.error('Erro:', error);
      });
  } else {
    console.error('Nenhum arquivo selecionado.');
  }
};

// Verifica se uma rota foi fornecida.
export const uploadRoute = async (route: any, name: any) => {
  if (route) {
    const formData = new FormData();
    formData.append('file', route);
    formData.append('projectName', name);

    // Faz uma solicitação para enviar a rota para o servidor.
    await fetch('http://127.0.0.1:8080/api/csv/uploadRelation', {
      method: 'POST',
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response;
        }
        throw new Error('Erro ao enviar arquivo.');
      })
      .then((data) => {
        console.log('Resposta do servidor:', data);

      })
      .catch((error) => {
        console.error('Erro:', error);
      });
  } else {
    console.error('Nenhum arquivo selecionado.');
  }
};

// Função para fazer upload de um "inout" para o servidor.
export const uploadInOut = async (inOut: any, name: any) => {
  // Verifica se um "inout" foi fornecido.
  if (inOut) {
    const formData = new FormData();
    formData.append('file', inOut);
    formData.append('projectName', name);

    // Faz uma solicitação para enviar o "inout" para o servidor.
    await fetch('http://127.0.0.1:8080/api/csv/uploadInout', {
      method: 'POST',
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          return response;
        }
        throw new Error('Erro ao enviar arquivo.');
      })
      .then((data) => {
        console.log('Resposta do servidor:', data);

      })
      .catch((error) => {
        console.error('Erro:', error);
      });
  } else {
    console.error('Nenhum arquivo selecionado.');
  }
};

// Função para adicionar um projeto.
export const addProject = async (text: any) => {
  await fetch('http://127.0.0.1:8080/api/projects/register', {
    method: 'POST', headers: {
      'Content-Type': 'application/json',
    }, body: JSON.stringify({ name: text })
  })
    .then((response) => {
      if (response.ok) {
        return response;
      }
      throw new Error("Error em Adicionar o put")
    })
    .then((data) => {
      console.log('Resposta', data)
    })
    .catch((error) => {
      console.error('Error:', error)
    })
};
// Constante que faz a deleção do projeto
export const deleteProject = async (setOpen: any, handleOpenModal: any, id: String) => {
  setOpen(true);
  handleOpenModal();
  await fetch(`http://localhost:8080/api/projects/id=${id}`, {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
  })
  window.location.reload();
};


export const downloadCsv = async (array: any) => {
  await fetch('http://127.0.0.1:8080/api/processPaths', {
    method: 'POST', headers: {
      'Content-Type': 'application/json',
    }, body: JSON.stringify(array)
  })
    .then((response) => {
      if (response.ok) {
        return response;
      }
      throw new Error("Error em Adicionar o put")
    })
    .then((data) => {
      console.log('Resposta', data)
    })
    .catch((error) => {
      console.error('Error:', error)
    })
};