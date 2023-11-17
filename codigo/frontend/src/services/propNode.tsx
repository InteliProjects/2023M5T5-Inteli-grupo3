import { Handle, NodeProps } from "reactflow";

// Definindo uma enumeração para representar as posições possíveis dos elementos
enum Position {
  Left = 'left',
  Top = 'top',
  Right = 'right',
  Bottom = 'bottom',
}

// Definindo estilos CSS para diferentes tipos de nós
export const tank: React.CSSProperties = {
  borderRadius: "100%",
  background: "green",
  border: "20px solid #ddd",
  padding: "10px",
  width: "300px",
  height: "300px",
  fontSize: "100px",
  textAlign: "center",
};

export const valves: React.CSSProperties = {
  borderRadius: "7%",
  background: "red",
  border: "10px solid black",
  
  // padding: "10px",
  width: "450px",
  height: "200px",
  fontSize: "100px",
  textAlign: "center",
};

export const inOut: React.CSSProperties = {
  borderRadius: "100%",
  background: "orange",
  border: "20px solid yellow ",
  // paddingBottom:"50px",
  padding: "60px",
  width: "600px",
  height: "600px",
  fontSize: "100px",
  textAlign: "center",
};

export const mix: React.CSSProperties = {
  borderRadius: "10%",
  background: "green",
  border: "4px solid #ddd",
  padding: "10px",
  textAlign: "center",
};

// Função utilitária para mapear uma string de posição para a enumeração "Position"
function mapPositionToEnum(position: string): Position {
  switch (position) {
    case 'left':
      return Position.Left;
    case 'top':
      return Position.Top;
    case 'right':
      return Position.Right;
    case 'bottom':
      return Position.Bottom;
    default:
      return Position.Top;
  }
}

// Definindo tipos de nós personalizados com elementos gráficos e alças de conexão
export const nodeTypes = {
  TANQUE: ({ data }: NodeProps) => (
    <div style={{ ...tank }}>
      <Handle type="source" position={mapPositionToEnum("top")} />
      {data?.label}
      <Handle type="target" position={mapPositionToEnum("bottom")} />
    </div>
  ),
  VALVULA: ({ data }: NodeProps) => (
    <div style={{ ...valves }}>
      <Handle type="source" position={mapPositionToEnum("top")} />
      {data?.label}
      <Handle type="target" position={mapPositionToEnum("bottom")} />
    </div>
  ),
  INOUT: ({ data }: NodeProps) => (
    <div style={{ ...inOut }}>
      <Handle type="source" position={mapPositionToEnum("top")} />
      {data?.label}
      <Handle type="target" position={mapPositionToEnum("bottom")} />
    </div>
  ),
  mixproof: ({ data }: NodeProps) => (
    <div style={{ ...mix }}>
      <Handle type="source" position={mapPositionToEnum("top")} />
      {data?.label}
      <Handle type="target" position={mapPositionToEnum("bottom")} />
    </div>
  )
};
