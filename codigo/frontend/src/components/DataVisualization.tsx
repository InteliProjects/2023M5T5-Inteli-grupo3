import { useEffect, useRef } from 'react';
import * as d3 from 'd3';

const Graph = ({ cor }: any) => {
  const svgRef = useRef(null);

  useEffect(() => {
    // Tamanho do SVG e espaçamento entre os nós
    const width = 200;
    const height = 150;
    const nodeRadius = 5;

    //define um tipo qualquer para as variaveis
    
    let nodesData:any
    let linksData:any

    switch (cor) {
      case 1:
        // conjunto de nós
        nodesData = [
          { "id": 0, "category": "init", "name": "node0", },
          { "id": 1, "category": "valve", "name": "node1" },
          { "id": 2, "category": "valve", "name": "node2" },
          { "id": 3, "category": "valve", "name": "node3" },
          { "id": 4, "category": "tank", "name": "node4" },

        ];
        // conjunto de arestas
        linksData = [
          { "source": 0, "target": 1, "value": 2 },
          { "source": 1, "target": 2, "value": 2 },
          { "source": 2, "target": 3, "value": 2 },
          { "source": 3, "target": 4, "value": 2 },
        ]
        break;
      case 2:
        // conjunto de nós
        nodesData = [
          { "id": 0, "category": "init", "name": "node0", },
          { "id": 1, "category": "valve", "name": "node1" },
          { "id": 2, "category": "valve", "name": "node2" },
          { "id": 3, "category": "valve", "name": "node3" },
          { "id": 4, "category": "valve", "name": "node4" },
          { "id": 5, "category": "valve", "name": "node5" },
          { "id": 6, "category": "tank", "name": "node6", },
        ];
        // conjunto de arestas
        linksData = [
          { "source": 0, "target": 1, "value": 2 },
          { "source": 1, "target": 2, "value": 2 },
          { "source": 2, "target": 3, "value": 2 },
          { "source": 3, "target": 4, "value": 2 },
          { "source": 4, "target": 5, "value": 2 },
          { "source": 5, "target": 6, "value": 2 },

        ]
        break;
      case 3:
        // conjunto de nós
        nodesData = [{ "id": 0, "category": "init", "name": "node0", },
        { "id": 1, "category": "valve", "name": "node1" },
        { "id": 2, "category": "valve", "name": "node2" },
        { "id": 3, "category": "valve", "name": "node3" },
        { "id": 4, "category": "valve", "name": "node4" },
        { "id": 5, "category": "valve", "name": "node5" },
        { "id": 6, "category": "valve", "name": "node6", },
        { "id": 7, "category": "tank", "name": "node7" },
        ]
        // conjunto de arestas
        linksData = [
          { "source": 0, "target": 1, "value": 2 },
          { "source": 1, "target": 2, "value": 2 },
          { "source": 2, "target": 3, "value": 2 },
          { "source": 3, "target": 4, "value": 2 },
          { "source": 4, "target": 5, "value": 2 },
          { "source": 5, "target": 6, "value": 2 },
          { "source": 6, "target": 7, "value": 2 },
        ]
        break;

    }


    // Cria um layout de força para posicionar os nós
    nodesData.forEach((node: any, index: any) => {
      node.x = index * 20; // Ajusta o valor para o espaçamento horizontal desejado
      node.y = index % 2 === 0 ? 50 : 100; // Alterna entre duas alturas para criar um zig-zag
    });

    // Cria um layout de força com as posições iniciais definidas
    const simulation = d3
      .forceSimulation(nodesData)
      .force('link', d3.forceLink().id((d: any) => d.id).links(linksData))
      .force('charge', d3.forceManyBody().strength(-3))
      .force('center', d3.forceCenter(width / 2, height / 2));

    // Seleciona o elemento SVG usando useRef
    const svg = d3.select(svgRef.current);

    // Cria grupos (g) para os nós
    const nodeGroups = svg
      .selectAll('.node')
      .data(nodesData)
      .enter()
      .append('g')
      .attr('class', 'node');

    // Cria os círculos representando os nós
    nodeGroups
      .append('circle')
      .attr('r', nodeRadius)
      .attr('fill', (d: any) => { if (d.category == "init") { return "black" } else { if (d.category == "tank") { return "aqua" } else { return "red" } } });

    // Cria as linhas que conectam os nós
    const linkLines = svg
      .selectAll('line')
      .data(linksData)
      .enter()
      .append('line')
      .attr('stroke', 'green')
      .attr('stroke-width', function (d: any) {
        return d.value;
      });

    // Atualiza a posição dos nós e das linhas a cada iteração da simulação
    simulation.on('tick', () => {
      nodeGroups.attr('transform', (d: any) => `translate(${d.x},${d.y})`);
      linkLines
        .attr('x1', (d: any) => d.source.x)
        .attr('y1', (d: any) => d.source.y)
        .attr('x2', (d: any) => d.target.x)
        .attr('y2', (d: any) => d.target.y);
    })

  });

  return <svg ref={svgRef == null ? null : svgRef} width={200} height={150}></svg>;
};

export default Graph;
