import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Autocomplete from '@mui/material/Autocomplete';
import {useEffect, useState} from "react";
import {Project} from "./GridCard"
import UploadModal from "./UploadModal";

export default function SearchSB() {
    // Constante que armazena se o modal de upload deve abir
    const [openUpload, setOpenUpload] = useState(false),

        // Faz o GET dos projetos e os armazena na constante
        [projects, setProject] = useState<Project[]>([]),
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
        },

        // Verifica se o usuário está tentando completar a sua pesquisa
        [optionSelected, setOptionSelected] = useState(false),
        handleOptionSelected = (event: any, option: any) => {
            if (option) {
                setOptionSelected(true);
            } else {
                setOptionSelected(false);
            }
        },

        // Se usuário seleciona nome válido, site redireciona para a página. Caso contrário, vai para o modal de criação
        handleOpenProject = (event: any) => {
            if (event.key === "Enter" && !optionSelected) {
                const element = document.getElementById("Project list");
                if (element) {
                    const value = element.getAttribute("value");
                    if (projects.some((project) => project.name === value)) {
                        window.location.href = `project?name=${value}`
                    } else {
                        setOpenUpload(true);
                    }
                }
            } else if (event.key === "Enter" && optionSelected) {
                setOptionSelected(false);
            }
        };


    // Load da função GET
  useEffect(() => {
    getProjects();
  }, []);
  
  return (
    <Stack spacing={2} sx={{ width: 250, marginLeft:3, marginTop: 5}} >
      <Autocomplete
        freeSolo
        id="Project list"
        disableClearable
        onHighlightChange={handleOptionSelected}
        options={projects.map((project) => project.name)}
        renderInput={(params) => (
          <TextField 
            {...params}
            label="Search Project"
            onKeyDown={handleOpenProject}
            InputProps={{
              ...params.InputProps,
              type: 'search',
            }}
          />
        )}
      />
        <UploadModal openState={openUpload} onClose={() => setOpenUpload(false)} />
    </Stack>
  );
}
