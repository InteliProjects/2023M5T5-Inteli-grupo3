import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { CardActionArea } from '@mui/material';
import IconButton from "@mui/material/IconButton";
import AddIcon from '@mui/icons-material/Add';
import { useState } from "react"
import UploadModal from "./UploadModal";

export default function UploadCard() {

  // Armazena o estado de vizualização do modal de novo projeto
  const [open, setOpen] = useState(false);

  return (
      <Card>
        <CardActionArea sx={{width: 200, height: 200}}>
          <CardContent sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-evenly",
            alignItems: "center",
            justifyItems: "center",
          }}>
            <IconButton
                color="primary"
                aria-label="upload project"
                component="label"
                onClick={() => setOpen(true)}
            >
              <AddIcon sx={{fontSize: 70, color: "black"}}/>
            </IconButton>
            <UploadModal openState={open} onClose={() => setOpen(false)} />
          </CardContent>
        </CardActionArea>
      </Card>
  );
}
