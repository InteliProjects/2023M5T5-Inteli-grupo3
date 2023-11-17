package com.inteli.onebeertwo.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.inteli.onebeertwo.models.PROJECT;

/**
 * Repositório para operações de banco de dados relacionadas a projetos.
 */
public interface PROJECTRepository extends Neo4jRepository<PROJECT, Long> {

  /**
   * Encontra um projeto pelo nome.
   *
   * @param name O nome do projeto a ser encontrado.
   * @return Um Optional contendo o projeto, se encontrado.
   */
  Optional<PROJECT> findPROJECTByName(String name);

  /**
   * Encontra um projeto pelo ID.
   *
   * @param id O ID do projeto a ser encontrado.
   * @return Um Optional contendo o projeto, se encontrado.
   */
  Optional<PROJECT> findPROJECTById(Long id);

  /**
   * Recupera os nós de um projeto especificado.
   *
   * @param projectName O nome do projeto para o qual obter os nós.
   * @return Uma representação JSON dos nós do projeto.
   */
  @Query(
      "MATCH (n:PROJECT {name: $projectName})\n" + //
      "MATCH (n)-[:CONTAINS_NODE]->(adjacentNode)\n" + //
      "WITH COLLECT(adjacentNode) AS nodes\n" + //
      "RETURN apoc.convert.toJson(nodes) AS jsonResult"
  )
  String getProjectNodes(@Param("projectName") String projectName);

  /**
   * Recupera os caminhos de um projeto especificado.
   *
   * @param projectName O nome do projeto para o qual obter os caminhos.
   * @return Uma representação JSON dos caminhos do projeto.
   */
  @Query(
      "MATCH (n:PROJECT {name: $projectName})\n" + //
      "MATCH (n)-[:CONTAINS_NODE]->(adjacentNode)\n" + //
      "MATCH (adjacentNode)-[r]->(t)\n" + //
      "WITH ID(r) AS id, ID(adjacentNode) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type\n" + //
      "WITH COLLECT({id: id, sourceId: sourceId, targetId: targetId, length: length, type: type}) AS paths\n" +
      "RETURN apoc.convert.toJson(paths) AS jsonResult"
  )
  String getProjectPaths(@Param("projectName") String projectName);
}
