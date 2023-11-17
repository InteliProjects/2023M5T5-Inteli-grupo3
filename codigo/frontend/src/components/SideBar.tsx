import {Box} from '@mui/material';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import {IconButton} from '@mui/material';
import Divider from '@mui/material/Divider';
import QuestionMarkIcon from '@mui/icons-material/QuestionMark';
import SearchSB from './SearchSB';
import WebhookIcon from '@mui/icons-material/Webhook';
import Typography from '@mui/material/Typography';
import Manual from "./Manual";
import {useState} from "react";

const drawerWidth = 300;

export default function PermanentDrawerLeft() {

    // Armazena o estado de visualização
    const [openManual, setOpenManual] = useState(false);

    return (
        <Box sx={{display: 'flex'}}>
            <CssBaseline/>
            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },
                }}
                variant="permanent"
                anchor="left"
            >
                <Box sx={{display: "flex", margin: 1, alignItems: "center"}}>
                    <WebhookIcon sx={{
                        fontSize: 50
                    }}/>
                    <Typography sx={{marginTop: 1, marginLeft: 2, fontWeight: "bold"}} gutterBottom variant="h5"
                                component="div">
                        Umberto
                    </Typography>
                </Box>
                <Divider/>
                <SearchSB/>
                <Box
                    sx={{
                        position: "fixed",
                        bottom: 0,
                        width: drawerWidth,
                        padding: "16px",
                        display: "flex",
                        justifyContent: "end",
                        alignItems: "center",
                    }}
                >
                    <IconButton>
                        <QuestionMarkIcon onClick={() => setOpenManual(true)} sx={{color: "#000000"}}/>
                    </IconButton>
                    <Manual isOpen={openManual} onClose={() => setOpenManual(false)}/>
                </Box>
            </Drawer>
        </Box>
    );
}
