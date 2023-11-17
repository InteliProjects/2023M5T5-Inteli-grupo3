package com.inteli.onebeertwo.services;

import com.inteli.onebeertwo.models.PROJECT;
import com.inteli.onebeertwo.repositories.PROJECTRepository;
import com.inteli.onebeertwo.requests.PROJECTRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PROJECTService {
  
    /**
     * Injeção do repositório de PROJECT
     */
    @Autowired
    private PROJECTRepository projectRepository;

    /**
     * Método para criar uma nova válvula PROJECT com base na requisição.
     *
     * @param request A requisição para criar a válvula PROJECT.
     * @return A válvula PROJECT criada.
     */
    public PROJECT createPROJECT(PROJECTRequest request) {
        PROJECT project = new PROJECT();
        project.setName(request.getName());
        projectRepository.save(project);
        return project;
    }

    public PROJECT getProject(Long id) {
        return projectRepository.findPROJECTById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para obter todas as PROJECTs.
     *
     * @return Uma lista de todas as válvulas PROJECT.
     */
    public List<PROJECT> getAllPROJECTs() {
        return projectRepository.findAll();
    }

    /**
     * Método para obter uma PROJECT pelo nome.
     *
     * @param name O nome da PROJECT a ser procurada.
     * @return A PROJECT correspondente.
     * @throws ResponseStatusException com status HTTP 404 se a PROJECT não for encontrada.
     */
    public PROJECT getPROJECTByName(String name){
        return projectRepository.findPROJECTByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para atualizar uma PROJECT.
     *
     * @param id O id da PROJECT a ser atualizada.
     * @param request A requisição com os dados para atualizar a PROJECT.
     * @return A PROJECT atualizada.
     * @throws ResponseStatusException com status HTTP 404 se a PROJECT não for encontrada.
     */
    public PROJECT updatePROJECT(Long id, PROJECTRequest request) {
        PROJECT project = projectRepository.findPROJECTById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(request, project);
        projectRepository.save(project);
        return project;
    }

    /**
     * Método para deletar uma PROJECT.
     *
     * @param id O id da PROJECT a ser deletada.
     * @throws ResponseStatusException com status HTTP 404 se a PROJECT não for encontrada.
     */
    public void deletePROJECT(Long id) {
        PROJECT project = projectRepository.findPROJECTById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        projectRepository.delete(project);
    }

    public String getProjectNodes(String name) {
        return projectRepository.getProjectNodes(name);
    }

    public String getProjectPaths(String name) {
        return projectRepository.getProjectPaths(name);
    }

}
