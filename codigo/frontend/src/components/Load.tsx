import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import load from "./load.svg"
import {Fragment} from 'react'

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

export default function Load({ modal }: any) {
    // O componente recebe a prop "modal" para determinar se o modal deve ser exibido

    return (
        <Fragment>
            <Modal
                open={modal} // Define se o modal deve ser exibido ou não com base na prop "modal"
                aria-labelledby="child-modal-title"
                aria-describedby="child-modal-description"
            >
                <Box sx={{ ...style, width: 400, height: 300, borderRadius: 5, display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center" }}>
                    <img src={load} width={70} height={70} alt="Carregando..." /> {/* Exibe uma imagem de carregamento */}
                </Box>
            </Modal>
        </Fragment>
    )
}
