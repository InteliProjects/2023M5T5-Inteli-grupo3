import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import Card from './Card';
import UploadCard from './UploadCard';
import {useEffect, useState} from "react";
import Load from "./Load";

// Interface de projeto para mapeamnto
export interface Project {
    id: string;
    name: string;
};

export default function SpacingGrid() {

    // Constante que faz o GET dos projetos
    const [projects, setProject] = useState<Project[]>([]), [open, setOpen] = useState(true),
        getProjects = async () => {
            try {
                const response = await fetch("http://127.0.0.1:8080/api/projects/");
                const json = await response.json();

                const projectsList: Project[] = json.map((i: any) => ({
                    id: `${i.id}`,
                    name: `${i.name}`
                }));

                setProject(projectsList);

            } catch (error) {
                console.error(error);
            }
            setOpen(false);
        };

    // Load da função GET
    useEffect(() => {
        getProjects();
    }, []);

    // Constante que faz o mapeamento JSON -> Componente
    const displayProjects = () => {
        if(projects.length != 0) {
            return projects.map((project) => (
                <Grid>
                    <Card key={project.id} id={project.id} name={project.name}/>
                </Grid>
            ))
        }
    };

    // Retorno dos componentes
    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={4}
                  alignItems="center"
                  justifyContent="center"
                  sx={{minHeight: '100vh'}}
            >
                <Grid><UploadCard/></Grid>
                {displayProjects()}
            </Grid>
            <Load modal={open}/>
        </Box>
    );
}


