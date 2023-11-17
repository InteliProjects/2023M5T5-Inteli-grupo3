import { useState, useEffect } from "react";
import styles from "../styles/home.module.css";
import Square from "../components/Square";
import DropLeft from "../components/DropLeft";
import Load from "../components/Load";
import * as d3 from 'd3';// Importando a biblioteca D3.js
import ButtonFloat from "../components/ButtonFloat"

import { getValves, getRoutes, getCaminho, downloadCsv } from "../services/asyncServices" //importando funções assincronas
import { useParams } from 'react-router-dom';
import queryString from 'query-string';

// Declaração das interfaces que representam os tipos de dados utilizados no componente
interface Node {
  id: string;
  type: string;
  position: { x: number; y: number };
  data: { label: string };
}

interface Route {
  id: string;
  source: string;
  target: string;
  label: string;
  type: string;
  style: { strokeWidth: number; stroke: string };
}


export default function Project() {
  const [route, setRouter] = useState<Route[]>([]);//variavel que guardará as rotas
  const [node, setNode] = useState<Node[]>([]);//variavel que guardará as valvulas



  const [start, setStart] = useState<string>("");//variavel que guarda o no de entrada
  const [goal, setGoal] = useState<string>("");//variavel que guarda o no de saida
  const [restriction1, SetRestriction1] = useState<any>(null);//variavel que guarda a restrição do caminho
  const [restriction2, SetRestriction2] = useState<any>(null);//variavel que guarda a restrição do caminho

  const [open, setOpen] = useState<boolean>(false)//variavel que guarda o estado do modal
  const [button, setButton] = useState<any>([])//variavel que guarda quantos botões ficaram ativos

  //variaveis que define 3 rotas diferentes
  const [routers, setRouters] = useState<any>([]);
  const [routers1, setRouters1] = useState<any>([]);
  const [routers2, setRouters2] = useState<any>([]);


  // Parse dos parâmetros da URL
  const queryParams = queryString.parse(window.location.search);
  const idName = queryParams.name;

  useEffect(() => {
    const loading = async () => {
      setOpen(true)
      setRouter([]);
      setNode([])
      setNode(await getValves(idName));
      setRouter(await getRoutes(idName));
      setOpen(false)

    }
    loading()

  }, [])

  // Funções que atualizam o estado com os valores de início e destino
  const startTarget = (e: any) => {
    setStart(e.target.value);
  };

  const goalTarget = (e: any) => {
    setGoal(e.target.value);

  };

  const restriction1Target = (e: any) => {
    SetRestriction1(e.target.value);


  };
  const restriction2Target = (e: any) => {
    SetRestriction2(e.target.value);
  };





  // Função para obter rotas e valvulas
  const getRouters = async () => {
    const response = await fetch(`http://127.0.0.1:8080/api/paths/AllPossibilities?begin=${start}&end=${goal}&maxDepth=10`)
    const json = await response.json()

    //salva as 3 melhores rotas e busca por seu id em cada endpoin
    const array = json.slice(0, 3).map((i: any) =>
      i.caminho)
    await downloadCsv(array)
    for (let n: number = 0; n < array.length; n++) {
      const mCaminhoP = await array[n].map(async (i: any) => {
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
      })

      //ao longo q n é incrementado salva as melhores rotas em cada variavel
      if (n === 0) {
        const promise = await Promise.all(mCaminhoP)
        const out: any = promise.map((i: any) => ({
          id: `${i.id}`,
          type: `${i.type}`,
          data: { label: `${i.name}` }
        }))

        //Usando a biblioteca da d3 para "espalhar" os nodos uniformemente
        const simulation = d3.forceSimulation(out)
          .force('charge', d3.forceManyBody().strength(-2000)) // Força de repulsão
          .force('link', d3.forceLink([]).id((d: any) => d.id).distance(100)) // Força de ligação
          .force('center', d3.forceCenter(1000, 900)) // Posição central

        // Execute a simulação
        simulation.tick(300);

        let hx = 0
        const saida: any = out.map((d: any) => ({
          ...d,
          position: {
            x: hx = hx + 250,
            y: 500
          }
        }))
        setButton([])
        button.push(0)
        setButton([...button])
        setRouters(saida)

      }
      //segue a mesma logica de cima 
      if (n === 1) {
        const promise = await Promise.all(mCaminhoP)
        const out: any = promise.map((i: any) => ({
          id: `${i.id}`,
          type: `${i.type}`,
          data: { label: `${i.name}` }
        }))

        const simulation = d3.forceSimulation(out)
          .force('charge', d3.forceManyBody().strength(-2000)) // Força de repulsão
          .force('link', d3.forceLink([]).id((d: any) => d.id).distance(100)) // Força de ligação
          .force('center', d3.forceCenter(1000, 900)) // Posição central

        // Execute a simulação
        simulation.tick(300);

        let hx = 0
        const saida: any = out.map((d: any) => ({
          ...d,
          position: {
            x: hx = hx + 250,
            y: 500
          }
        }))
        setButton([])
        button.push(1)
        setButton([...button])
        setRouters1(saida)

      }
      //segue a mesma logica de cima 
      if (n === 2) {
        const promise = await Promise.all(mCaminhoP)
        const out: any = promise.map((i: any) => ({
          id: `${i.id}`,
          type: `${i.type}`,
          data: { label: `${i.name}` }
        }))

        const simulation = d3.forceSimulation(out)
          .force('charge', d3.forceManyBody().strength(-2000)) // Força de repulsão
          .force('link', d3.forceLink([]).id((d: any) => d.id).distance(100)) // Força de ligação
          .force('center', d3.forceCenter(1000, 900)) // Posição central

        // Execute a simulação
        simulation.tick(300);

        let hx = 0
        const saida: any = out.map((d: any) => ({
          ...d,
          position: {
            x: hx = hx + 250,
            y: 500
          }
        }))
        setButton([])
        button.push(2)
        setButton([...button])
        setRouters2(saida)

      }
    }
  }
  //função que verifica se foi digitado algum caminho, se for retorna os 3 melhores caminho, se não, retorna o mapa da fabrica 
  const getAll = async () => {
    setOpen(true)
    setRouter([]);
    setNode([])
    setNode(await getCaminho({ start, goal, restriction1, restriction2 }));
    setRouter(await getRoutes(idName));
    await getRouters()
    setOpen(false)
  };
  //usado para construir a visualizaçao das melhores rotas
  const rota1 = async () => {
    if (routers != "") {
      setNode([])
      setRouter([]);
      setRouter(await getRoutes(idName));
      setNode(routers)
      setRouters(node)
    } else {
      console.log("Não há rota")
    }


  }
  //usado para construir a visualizaçao das melhores rotas

  const rota2 = async () => {
    if (routers1 != "") {
      setNode([])
      setRouter([]);
      setRouter(await getRoutes(idName));
      setNode(routers1)
      setRouters1(node)
    } else {
      console.log("Não há rota")

    }


  }
  //usado para construir a visualizaçao das melhores rotas

  const rota3 = async () => {
    if (routers2 != "") {
      setNode([])
      setRouter([]);
      setRouter(await getRoutes(idName));
      setNode(routers2)
      setRouters2(node)
    } else {
      console.log("Não há rota")


    }

  }

  return (
    <div className={styles.container}>
      <DropLeft getAll={getAll} start={startTarget} goal={goalTarget} rota1={rota1} rota2={rota2} rota3={rota3} ndsvg={button} restriction1={restriction1Target} restriction2={restriction2Target} />
      <ButtonFloat />
      <Square node={node} route={route} />
      <Load modal={open} />

    </div>
  );
}
