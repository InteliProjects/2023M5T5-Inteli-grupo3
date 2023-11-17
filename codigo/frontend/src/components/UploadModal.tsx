import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
import Stack from "@mui/material/Stack";
import PropaneTankIcon from "@mui/icons-material/PropaneTank";
import TuneIcon from "@mui/icons-material/Tune";
import LoginIcon from "@mui/icons-material/Login";
import RouteIcon from "@mui/icons-material/Route";
import {useState} from "react";
import {addProject, uploadInOut, uploadRoute, uploadTank, uploadValve} from "../services/asyncServices";
import {styled} from "@mui/material/styles";
import Load from "./Load";

export default function UploadModal({openState, onClose}: { openState: boolean, onClose: () => void }) {

// Estilo do modal
    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        boxShadow: 24,
        p: 4,
    };

    // Lida com o botão de upload dos tanques
    const [tank, setTank] = useState(null),
        [greenTank, setGreenTank] = useState(false),
        handleTank = (e: any) => {
            setTank(e.target.files[0]);
            setGreenTank(true);
        },
        // Lida com o botão de upload das válulas
        [valve, setValves] = useState(null),
        [greenValve, setGreenValve] = useState(false),
        handleValve = (e: any) => {
            setValves(e.target.files[0]);
            setGreenValve(true);
        },
        // Lida com o botão de upload dos In&Outs
        [inOut, setInOut] = useState(null),
        [greenInOut, setGreenInOut] = useState(false),
        handleInOut = (e: any) => {
            setInOut(e.target.files[0]);
            setGreenInOut(true);
        },
        // Lida com o botão de upload dos relacionamentos
        [route, setRoutes] = useState(null),
        [greenRoute, setGreenRoute] = useState(false),
        handleRoute = (e: any) => {
            setRoutes(e.target.files[0]);
            setGreenRoute(true);
        },
        // Lida com o nome do projeto inputtado no modal
        [projectName, setProjectName] = useState(""),
        handleText = (e: any) => {
            const inputValue = e.target.value;
            const alphanumericPattern = /^[a-zA-Z0-9\s_]*$/;

            if (inputValue === '' || alphanumericPattern.test(inputValue)) {
                const formattedValue = inputValue.replace(/\s/g, '_');
                setProjectName(formattedValue);
                setNameError(false);
            }
        },
        // Armazena erros em nome (Lida com string vazia)
        [nameError, setNameError] = useState(false),
        // Armazena a visibilidade dos botões para o upload dos CSVs
        [openCSV, setOpenCSV] = useState(false),
        // Armazena a visibilidade do modal de carregamento
        [openLoad, setOpenLoad] = useState(false);

    // Lida com o botão de download do modelo de CSV
    const handleDownloadClick = () => {
            const link = document.createElement('a');
            link.href = 'oneBeerTwoCSV.zip';
            link.download = 'oneBeerTwoCSV.zip';
            link.click();
            setOpenCSV(true);
        },
        // Função para fazer o upload de arquivos e adicionar um novo projeto
        upload = async () => {
            setOpenLoad(true);
            if (projectName !== "") {
                setOpenLoad(true);
                await addProject(projectName)
                await uploadTank(tank, projectName)
                await uploadValve(valve, projectName)
                await uploadInOut(inOut, projectName)
                await uploadRoute(route, projectName)
                window.location.href = `/project?name=${projectName}`
            } else {
                setNameError(true);
                const element = document.getElementById("projName");
                if (element) {
                    element.focus();
                }
            }
        },
        // Faz com que o modal feche no botão de cancelar
        handleClose = () => {
            onClose();
        }

    // Modal para adicionar um novo projeto
    const VisuallyHiddenInput = styled('input')({
        clip: 'rect(0 0 0 0)',
        clipPath: 'inset(50%)',
        height: 1,
        overflow: 'hidden',
        position: 'absolute',
        bottom: 0,
        left: 0,
        whiteSpace: 'nowrap',
        width: 1,
    });

    // Retorna os componentes
    return (
        <Modal open={openState} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
            <>
                <Load open={openLoad}/>
                <Box sx={{
                    ...style,
                    width: 700,
                    height: 500,
                    borderRadius: 5,
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center"
                }}>
                    <Typography gutterBottom variant="h5" component="div" textAlign={"center"} sx={{margin: "20px"}}>
                        Para adicionar um novo projeto, preencha o formulário abaixo
                    </Typography>
                    <TextField id="projName" label="Nome do projeto" variant="standard" value={projectName}
                               onChange={handleText}
                               sx={{marginTop: "60px", marginBottom: "50px"}} inputProps={{maxLength: 15}}
                               color={nameError ? "error" : "primary"}/>
                    <Button component="label" variant="contained" startIcon={<FormatListBulletedIcon/>}
                            onClick={handleDownloadClick}>
                        Download do modelo de CSV
                    </Button>
                    {openCSV ? (
                        <>
                            <Stack spacing={2} direction="row" sx={{margin: "10px", width: "auto"}}>
                                <Button component="label" variant="contained" startIcon={<PropaneTankIcon/>}
                                        color={greenTank ? "success" : "primary"}>
                                    Upload CSV de tanques
                                    <VisuallyHiddenInput type="file" onChange={handleTank}/>
                                </Button>
                                <Button component="label" variant="contained" startIcon={<TuneIcon/>}
                                        color={greenValve ? "success" : "primary"}>
                                    Upload CSV das válvulas
                                    <VisuallyHiddenInput type="file" onChange={handleValve}/>
                                </Button>
                            </Stack>
                            <Stack spacing={2} direction="row">
                                <Button component="label" variant="contained" startIcon={<LoginIcon/>}
                                        color={greenInOut ? "success" : "primary"}>
                                    Upload CSV de "In&Outs"
                                    <VisuallyHiddenInput type="file" onChange={handleInOut}/>
                                </Button>
                                <Button component="label" variant="contained" startIcon={<RouteIcon/>}
                                        color={greenRoute ? "success" : "primary"}>
                                    Upload CSV de tubulações
                                    <VisuallyHiddenInput type="file" onChange={handleRoute}/>
                                </Button>
                            </Stack>
                        </>
                    ) : null}
                    <Stack spacing={2} direction="row" sx={{marginTop: '20px'}}>
                        <Button variant="outlined" color="error" onClick={handleClose}> Cancelar</Button>
                        <Button variant="contained" color="success" onClick={upload}>Adicionar</Button>
                    </Stack>
                </Box>
                <Load modal={openLoad}/>
            </>
        </Modal>
    );
}