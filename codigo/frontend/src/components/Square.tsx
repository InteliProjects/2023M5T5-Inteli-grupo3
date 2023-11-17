import { useCallback, useEffect, useState } from "react";
import styles from "../styles/square.module.css";
import "reactflow/dist/style.css";
import { nodeTypes } from "../services/propNode";
import ReactFlow, {
  Background,
  Controls,
  MiniMap,
  addEdge,
  applyNodeChanges,
  applyEdgeChanges,
  OnNodesChange,
  OnEdgesChange
} from 'reactflow';


interface Route {
  id: string;
  source: string;
  target: string;
  label: string;
  type: string;
  style: { strokeWidth: number; stroke: string };
}

interface Node {
  id: string;
  type: string;
  position: { x: number; y: number };
  data: { label: string };
}

interface SquareProps {
  route: Route[];
  node: Node[];
}

export default function Square({ route, node }: SquareProps) {
  const [routes, setRoutes] = useState<Route[]>([]); // Guarda todas as rotas de um nó a outro
  const [nodes, setNodes] = useState<Node[]>([]); // Guarda todos os nós

  useEffect(() => {
    setRoutes(route);
    setNodes(node);
  }, [route, node]);

  const onNodesChange: OnNodesChange = useCallback(
    (changes) => setNodes((nds): any => applyNodeChanges(changes, nds)),
    [setNodes]
  );

  const onEdgesChange: OnEdgesChange = useCallback(
    (changes) => setRoutes((eds): any => applyEdgeChanges(changes, eds)),
    [setRoutes]
  );

  return (
    <div className={styles.project}>
      <ReactFlow
        nodes={nodes}// Nó
        edges={routes}// Aresta
        onNodesChange={onNodesChange} // Permite a atualização da posição dos nós
        onEdgesChange={onEdgesChange} // Permite a atualização da posição das arestas
        nodeTypes={nodeTypes} // Tipos de nó definidos para o gráfico
        defaultViewport={{ x: 700, y: 400, zoom: 0.06 }} // posição inicial da vizualização dos nos
        minZoom={0.06}// zoom minimo
      >
        <Background /> {/* Plano de fundo do gráfico */}
        <Controls /> {/* Controles de zoom e navegação */}
        <MiniMap nodeStrokeWidth={1} /> {/* Mini mapa para navegação */}
      </ReactFlow>
    </div>
  );
}
