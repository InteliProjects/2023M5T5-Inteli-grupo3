import Card from '@mui/material/Card'; 
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {CardActionArea} from '@mui/material';
import {Link} from 'react-router-dom';
import styles from '../styles/search.module.css'
import WebhookIcon from '@mui/icons-material/Webhook';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Grow from '@mui/material/Grow';
import {useState} from "react";
import TextField from '@mui/material/TextField';
import CheckIcon from '@mui/icons-material/Check';
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import ReportIcon from "@mui/icons-material/Report";
import Button from "@mui/material/Button";
import Load from "./Load";
import {deleteProject} from "../services/asyncServices";

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

export default function ActionAreaCard({id, name}: any) {


    // Constante que armazena a URL de cada projeto
    let url = `../projects/id=${id}`;

    // Constante que armazena a visibilidade dos botões de edição e deleção
    const [actionsChecked, actionsSetChecked] = useState(false);

    // Constante que armazena o novo nome pós renomeação
    const [newName, setNewName] = useState(name);

    // Constante que armazena a visibilidade do modal de deleção
    const [openModal, setOpenModal] = useState(false);

    // Constante que habilita a tela de carregamento
    const [open, setOpen] = useState(false);

    // Constante que indica se o botão de edit foi pressionado
    const [editChecked, editSetChecked] = useState(false);

    // Constante que modifica o nome do projeto quando o usuário clica em OK
    const handleClickOut = async () => {
        editSetChecked((prev) => !(prev));

        // Constante que armazena o que o usuário digitou
        const updatedProject = {name: newName};

        if (name !== newName) {
            await fetch(`http://localhost:8080/api/projects/id=${id}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(updatedProject),
            })
        }
    };

    // Constante que controla a visibilidade do modal de deleção
    const handleOpenModal = () => {
        setOpenModal(!(openModal));
    };

    const handleDelete = () => {
        deleteProject(setOpen, handleOpenModal, id).then(r => console.log(r));
    }

    const handleTextEdit = (e: any) => {
        const inputValue = e.target.value;
        const alphanumericPattern = /^[a-zA-Z0-9\s'_']*$/;

        if (inputValue === '' || alphanumericPattern.test(inputValue)) {
            const formattedValue = inputValue.replace(/\s/g, '_');
            setNewName(formattedValue);
        }
    }

    // Componente dos botões de edição e deleção
    let actions = (
        <Stack direction={"row"} spacing={1} justifyContent={"space-around"}>
            {editChecked ? (
                <IconButton aria-label="confirm" onClick={handleClickOut}>
                    <CheckIcon/>
                </IconButton>
            ) : (
                <>
                    <IconButton aria-label="edit" onClick={() => editSetChecked(true)}>
                        <EditIcon/>
                    </IconButton>
                    <IconButton aria-label="delete" onClick={handleOpenModal}>
                        <DeleteIcon/>
                    </IconButton>
                </>
            )}
        </Stack>
    );

    // Constante que armazena o modal de deleção
    const deleteModal = (
        <Modal open={openModal}
               aria-labelledby="child-modal-title"
               aria-describedby="child-modal-description">
            <Box sx={{
                ...style,
                width: 700,
                height: 500,
                borderRadius: 5,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center"
            }}>
                <ReportIcon color={'error'} sx={{fontSize: '100px', marginBottom: '20px'}}/>
                <Typography gutterBottom variant="h5" component="div" textAlign={"center"}>
                    Tem certeza que quer remover esse projeto?
                </Typography>
                <Typography gutterBottom variant="subtitle1" component="div" textAlign={"center"}
                            sx={{marginBottom: "100px"}}>
                    Essa ação é irreversível.
                </Typography>
                <Stack spacing={2} direction="row" sx={{marginTop: '20px'}}>
                    <Button variant="outlined" color="error" onClick={handleOpenModal}>Cancelar</Button>
                    <Button variant="contained" color="error" onClick={handleDelete}>Remover</Button>
                </Stack>
            </Box>
        </Modal>
    );

    // Componente do nome do projeto
    let normalName = (
        <Link to={url} className={styles.link}>
            <Typography gutterBottom variant="h5" component="div" textAlign={"center"}>
                {newName}
            </Typography>
        </Link>
    );

    // Componente do nome do projeto em modo de edição
    let editName = (
        <div style={{textAlign: "center"}}>
            <TextField id="standard-basic"
                       label="Novo nome"
                       variant="standard"
                       sx={{width: "90%"}}
                       value={newName}
                       inputProps={{maxLength: 15}}
                       onChange={handleTextEdit}
            />
        </div>
    );

    // Retorno dos componentes
    return (
        <Card onMouseEnter={() => actionsSetChecked(true)} onMouseLeave={() => actionsSetChecked(false)}>
            <Link to={`../project?name=${newName}`} className={styles.link}>

                <CardActionArea sx={{width: 200, height: 200,}}>
                    <CardContent sx={
                        {
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "space-evenly",
                            alignItems: "center",
                            justifyItems: "center",
                        }
                    }
                    >
                        <WebhookIcon sx={{fontSize: 70}}/>
                    </CardContent>
                </CardActionArea>
            </Link>
            {editChecked ? (editName) : (normalName)}
            <Grow
                in={actionsChecked}
                style={{transformOrigin: '0 0 0'}}
                {...(actionsChecked ? {timeout: 1000} : {})}
            >
                {actions}
            </Grow>
            {deleteModal}
            <Load modal={open}/>
        </Card>
    );
}