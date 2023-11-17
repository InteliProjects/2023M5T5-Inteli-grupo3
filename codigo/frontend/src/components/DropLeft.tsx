import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import CssBaseline from "@mui/material/CssBaseline";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import Button from "@mui/material/Button";
import AddLocationIcon from "@mui/icons-material/AddLocation";
import RouteIcon from "@mui/icons-material/Route";
import ButtonGroup from "@mui/material/ButtonGroup";
import WebhookIcon from '@mui/icons-material/Webhook';
import Typography from '@mui/material/Typography';
import DataVisualization from "./DataVisualization"
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import { useState, useEffect } from "react"
import NativeSelect from '@mui/material/NativeSelect';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import queryString from 'query-string';
import { getValves } from '../services/asyncServices'


const drawerWidth = 240;

// Definindo as propriedades do componente DropLeft com os tipos correspondentes
interface DropLeftProps {
  getAll: () => void; // Função que não recebe argumentos e não retorna nada
  start: any; // Função que recebe um evento de mudança em um input como argumento e não retorna nada
  goal: any; // Função que recebe um evento de mudança em um input como argumento e não retorna nada
  restriction1: any; // Função que recebe um evento de mudança em um input como argumento e não retorna nada
  restriction2: any; // Função que recebe um evento de mudança em um input como argumento e não retorna nada
  rota1: () => void; // Função que não recebe argumentos e não retorna nada
  rota2: () => void; // Função que não recebe argumentos e não retorna nada
  rota3: any; // Função que não recebe argumentos e não retorna nada
  ndsvg: any
}

// Transformando o componente em uma função que recebe as propriedades definidas acima
export default function DropLeft({
  getAll,
  start,
  goal,
  rota1,
  rota2,
  rota3,
  restriction1,
  restriction2,
  ndsvg
}: DropLeftProps) {

  let ind0 = 0
  let ind = 0

  const [paths, setPaths] = useState<any>([])
  const [searchRoute, setSearchRoute] = useState<any>([])

  const queryParams = queryString.parse(window.location.search);
  const idName = queryParams.name;
  useEffect(() => {
    const getRoutes = async (link: any) => {
      const response = await fetch(`http://127.0.0.1:8080/api/projects/name=${link}/paths`);
      const json = await response.json();
      // Mapeia os dados obtidos para o formato desejado.
      const ids: any = json.map((i: any) => {
        return {
          label: i.type,
        };
      });
      const names: any = ids.filter((i: any) => i.type !== null)
      setPaths(names);
    };
    const load = async (params: any) => {
      setSearchRoute(await getValves(params))

    }
    load(idName)
    getRoutes(idName)

  }, [ndsvg])

  function removerNomesRepetidos(array: any) {
    var novoArray = [];
    for (var i = 0; i < array.length; i++) {
      if (novoArray.indexOf(array[i].label) === -1) {
        novoArray.push(array[i].label);
      }
    }
    const arrayFinal: any = novoArray.filter((i: any) => i !== null)
    return arrayFinal;
  }

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: drawerWidth,
            boxSizing: "border-box",
          },
        }}
        variant="permanent"
        anchor="left"
      >
        <Box sx={{ display: "flex", margin: 1, alignItems: "center" }}>
          <WebhookIcon sx={{ fontSize: 50 }} />
          <Typography
            sx={{ marginTop: 1, marginLeft: 2, fontWeight: "bold", color: 'inherit', textDecoration: 'none' }}
            gutterBottom
            variant="h5"
            component="a"
            href="/"


          >
            Umberto
          </Typography>
        </Box>

        <Divider />

        <List
          sx={{
            display: "flex",
            justifyContent: "space-evenly",
            justifyItems: "center",
            alignItems: "end",
            height: "6vw",
            paddingRight: "10px",
            paddingLeft: "10px",



          }}
        >

          <AddLocationIcon sx={{ mr: 1 }} />
          <FormControl fullWidth>
            <InputLabel variant="standard" htmlFor="uncontrolled-native">
              Origem</InputLabel>

            <NativeSelect
              onChange={start}
              inputProps={{
                name: 'Restriction-1',
                id: 'uncontrolled-native',
              }}
            >


              <option></option>
              {searchRoute
                .slice() // Crie uma cópia da matriz para evitar a alteração da ordem original
                .sort((a: any, b: any) => a.id - b.id) // Classifique os elementos com base em seus IDs
                .map((node: any) => (
                  <option key={node.id} value={node.id}>{` ID:${node.id} - ${node.type}-${node.data.label} `}</option>
                ))}
            </NativeSelect>
          </FormControl>
        </List>
        <List
          sx={{
            display: "flex",
            justifyContent: "space-evenly",
            justifyItems: "center",
            alignItems: "end",
            height: "6vw",
            paddingRight: "10px",
            paddingLeft: "10px",



          }}
        >

          <RouteIcon
            sx={{ mr: 1 }} />
          <FormControl fullWidth>
            <InputLabel variant="standard" htmlFor="uncontrolled-native"
            >Destino
            </InputLabel>
            <NativeSelect
              onChange={goal}

              inputProps={{

                name: 'Restriction-2',
                id: 'uncontrolled-native',
              }}
            >


              <option></option>
              {searchRoute
                .slice() // Crie uma cópia da matriz para evitar a alteração da ordem original
                .sort((a: any, b: any) => a.id - b.id) // Classifique os elementos com base em seus IDs
                .map((node: any) => (
                  <option key={node.id} value={node.id}>{` ID:${node.id} - ${node.type}-${node.data.label} `}</option>
                ))}
            </NativeSelect>
          </FormControl>

        </List>
        <List
          sx={{
            display: "flex",
            justifyContent: "space-evenly",
            justifyItems: "center",
            alignItems: "end",
            height: "6vw",
            paddingRight: "10px",
            paddingLeft: "10px",



          }}
        >

          <RemoveCircleOutlineIcon sx={{ mr: 1 }} />
          <FormControl fullWidth>
            <InputLabel variant="standard" htmlFor="uncontrolled-native" >
              Restrição-1
            </InputLabel>
            <NativeSelect


              onChange={restriction1}
              inputProps={{
                name: 'Restriction-2',
                id: 'uncontrolled-native',
              }}
            >


              <option value={"null"}></option>

              {removerNomesRepetidos(paths).map((tubulacao: any) => (
                <option value={tubulacao} >{` ${ind++} - ${tubulacao}`}</option>

              ))}
            </NativeSelect>
          </FormControl>

        </List>
        <List
          sx={{
            display: "flex",
            justifyContent: "space-evenly",
            justifyItems: "center",
            alignItems: "end",
            height: "6vw",
            paddingRight: "10px",
            paddingLeft: "10px",



          }}
        >
          <RemoveCircleOutlineIcon sx={{ mr: 1 }} />
          <FormControl fullWidth>
            <InputLabel variant="standard" htmlFor="uncontrolled-native" >
              Restrição-2
            </InputLabel>
            <NativeSelect

              onChange={restriction2}
              inputProps={{
                name: 'Restriction-2',
                id: 'uncontrolled-native',
              }}
            >


              <option value={"null"}></option>

              {removerNomesRepetidos(paths).map((tubulacao: any) => (
                <option value={tubulacao} >{` ${ind0++} - ${tubulacao}`}</option>

              ))}
            </NativeSelect>
          </FormControl>

        </List>


        <Box sx={{ display: "flex", width: "100%", alignItems: "center", justifyContent: "center", marginTop: "20px", marginBottom: "20px" }}>
          <Button onClick={getAll} color="primary" variant="contained" sx={{ width: 10, marginBottom: 1 }}>
            Get
          </Button>
        </Box>


        <Divider />

        <ButtonGroup

          sx={{
            display: "flex",
            justifyItems: "space-evenly",
            alignItems: "start",
            height: "auto",
            width: "100%",
            justifyContent: "center",
            marginTop: 1,
            marginBottom: 1,
            // color:"red"
          }}


        >

          {ndsvg.length > 0 ? <Button color={"primary"} onClick={rota1}>R1</Button> : <Button variant="outlined" disabled>
            R1
          </Button>}
          {ndsvg.length > 1 ? <Button color={"primary"} onClick={rota2}>R2</Button> : <Button variant="outlined" disabled>
            R2
          </Button>}
          {ndsvg.length > 2 ? <Button color={"primary"} onClick={rota3}>R3</Button> : <Button variant="outlined" disabled>
            R3
          </Button>}

        </ButtonGroup>
        <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center", justifyItems: "center" }}>
          {ndsvg.length > 0 ? <DataVisualization cor={1} />
            : <></>}
          {ndsvg.length > 1 ? <DataVisualization cor={2} />
            : <></>}
          {ndsvg.length > 2 ? <DataVisualization cor={3} />
            : <></>
          }
        </Box>
      </Drawer>
    </Box>
  );
}
