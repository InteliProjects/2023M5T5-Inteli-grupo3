package com.inteli.onebeertwo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inteli.onebeertwo.models.PROJECT;
import com.inteli.onebeertwo.objects.PROJECTDTO;
import com.inteli.onebeertwo.requests.PROJECTRequest;
import com.inteli.onebeertwo.services.PROJECTService;

/**
 * Controlador para manipulação de projetos.
 */
@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class PROJECTController {

  /**
   * Serviço responsável pelas operações de projeto.
   */
  private final PROJECTService projectService;

  /**
   * Construtor do controlador que recebe um serviço de projeto.
   *
   * @param projectService O serviço de projeto a ser injetado.
   */
  public PROJECTController(PROJECTService projectService) {
    this.projectService = projectService;
  }

  /**
   * Obtém a lista de todos os projetos.
   *
   * @return Uma resposta HTTP contendo a lista de projetos e o status OK.
   */
  @GetMapping("/")
  public ResponseEntity<List<PROJECT>> projectIndex() {
    return new ResponseEntity<>(projectService.getAllPROJECTs(), HttpStatus.OK);
  }

  /**
   * Registra um novo projeto.
   *
   * @param request Os detalhes do projeto a ser registrado.
   * @return Uma resposta HTTP contendo os detalhes do projeto recém-criado e o status CREATED.
   */
  @PostMapping("/register")
  public ResponseEntity<PROJECTDTO> signUp(@RequestBody PROJECTRequest request) {
    PROJECT project = projectService.createPROJECT(request);

    PROJECTDTO responsePROJECT = new PROJECTDTO(project.getName());

    return new ResponseEntity<>(responsePROJECT, HttpStatus.CREATED);
  }

  /**
   * Obtém um projeto com base no ID.
   *
   * @param id O ID do projeto a ser recuperado.
   * @return Uma resposta HTTP contendo o projeto correspondente e o status OK.
   */
  @GetMapping("/id={id}")
  public ResponseEntity<PROJECT> getPROJECT(@PathVariable Long id) {
    return new ResponseEntity<>(projectService.getProject(id), HttpStatus.OK);
  }

  /**
   * Obtém um projeto com base no nome.
   *
   * @param name O nome do projeto a ser recuperado.
   * @return Uma resposta HTTP contendo o projeto correspondente e o status OK.
   */
  @GetMapping("/name={name}")
  public ResponseEntity<PROJECT> getPROJECTByName(@PathVariable String name) {
    return new ResponseEntity<>(projectService.getPROJECTByName(name), HttpStatus.OK);
  }

  /**
   * Atualiza um projeto existente com base no ID.
   *
   * @param id      O ID do projeto a ser atualizado.
   * @param request Os novos detalhes do projeto.
   * @return Uma resposta HTTP contendo o projeto atualizado e o status OK.
   */
  @PutMapping("/id={id}")
  public ResponseEntity<PROJECT> updatePROJECT(@PathVariable Long id, @RequestBody PROJECTRequest request) {
    PROJECT project = projectService.updatePROJECT(id, request);
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  /**
   * Exclui um projeto com base no ID.
   *
   * @param id O ID do projeto a ser excluído.
   * @return Uma resposta HTTP com o status OK.
   */
  @DeleteMapping("/id={id}")
  public ResponseEntity<PROJECT> deletePROJECT(@PathVariable Long id) {
    projectService.deletePROJECT(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Obtém os nós de um projeto com base no nome.
   *
   * @param name O nome do projeto para o qual obter os nós.
   * @return Uma resposta HTTP contendo os nós do projeto e o status OK.
   */
  @GetMapping("/name={name}/nodes")
  public ResponseEntity<String> getPROJECTNodes(@PathVariable String name) {
    String nodes = projectService.getProjectNodes(name);
    return new ResponseEntity<>(nodes, HttpStatus.OK);
  }

  /**
   * Obtém os caminhos de um projeto com base no nome.
   *
   * @param name O nome do projeto para o qual obter os caminhos.
   * @return Uma resposta HTTP contendo os caminhos do projeto e o status OK.
   */
  @GetMapping("/name={name}/paths")
  public ResponseEntity<String> getPROJECTPaths(@PathVariable String name) {
    String paths = projectService.getProjectPaths(name);
    return new ResponseEntity<>(paths, HttpStatus.OK);
  }
}
