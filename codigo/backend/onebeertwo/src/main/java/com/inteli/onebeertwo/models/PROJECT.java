package com.inteli.onebeertwo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

/**
 * Entidade que representa um projeto no sistema.
 */
public class PROJECT {
  
  /**
   * Identificador único do projeto.
   */
  @Id
  @GeneratedValue
  private Long id;
  
  /**
   * Nome do projeto.
   */
  private String name;

  /**
   * Construtor padrão.
   */
  public PROJECT() {
  }

  /**
   * Construtor com parâmetros para inicializar ID e nome do projeto.
   *
   * @param id   O identificador único do projeto.
   * @param name O nome do projeto.
   */
  public PROJECT(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Obtém o identificador único do projeto.
   *
   * @return O identificador único do projeto.
   */
  public Long getId() {
    return id;
  }

  /**
   * Define o identificador único do projeto.
   *
   * @param id O novo identificador único do projeto.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Obtém o nome do projeto.
   *
   * @return O nome do projeto.
   */
  public String getName() {
    return name;
  }

  /**
   * Define o nome do projeto.
   *
   * @param name O novo nome do projeto.
   */
  public void setName(String name) {
    this.name = name;
  }
}
