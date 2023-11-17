package com.inteli.onebeertwo.repositories;

import com.inteli.onebeertwo.models.Path;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface que define um repositório para interagir com os relacionamentos/arestas no grafo Neo4j.
 */
public interface PathRepository extends Neo4jRepository<Path, Long> {

    /**
     * @param sourceId
     * @param targetId
     * @return
     */
    @Query("MATCH (a)-[r:TUBULAÇÃO]->(b) WHERE ID(a) = $sourceId AND ID(b) = $targetId RETURN r.type")
    String getTubulationType(@Param("sourceId") Long sourceId, @Param("targetId") Long targetId);
    
    /**
     * Retorna o tipo de uma tubulação entre dois nós com os nomes fornecidos.
     *
     * @param sourceId O nome do nó de origem.
     * @param targetId O nome do nó de destino.
     * @return O tipo da tubulação.
     */

    @Query("MATCH (a)-[r:TUBULAÇÃO]->(b) WHERE a.name = $sourceId AND b.name = $targetId RETURN r.type")
    String getTubulationTypeByName(@Param("sourceId") String sourceId, @Param("targetId") String targetId);
    

    /**
     * Retorna todos os caminhos presentes no grafo.
     *
     * @return Uma lista de objetos Path representando os caminhos.
     */
    @Query("MATCH (s)-[r]->(t) RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type")
    List<Path> fetchPaths();
    
    /**
     * Retorna um caminho específico pelo seu ID.
     *
     * @param id O ID do caminho a ser procurado.
     * @return Um objeto Path representando o caminho, se encontrado.
     */
    @Query("MATCH (s)-[r]->(t) WHERE ID(r) = $id RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type")
    Path fetchPathById(@Param("id") Long id);

    /**
     * Retorna todos os caminhos que têm o nó de origem com o ID fornecido.
     *
     * @param id O ID do nó de origem.
     * @return Uma lista de objetos Path representando os caminhos.
     */
    @Query("MATCH (s)-[r]->(t) WHERE ID(s) = $id RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type")
    List<Path> fetchSourceNeighbors(@Param("id") Long id);

    /**
     * Retorna todos os caminhos que têm o nó de destino com o ID fornecido.
     *
     * @param id O ID do nó de destino.
     * @return Uma lista de objetos Path representando os caminhos.
     */
    @Query("MATCH (s)-[r]->(t) WHERE ID(t) = $id RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type")
    List<Path> fetchTargetNeighbors(@Param("id") Long id);

    /**
     * Cria um novo caminho entre dois nós com os IDs fornecidos.
     *
     * @param sourceId O ID do nó de origem.
     * @param targetId O ID do nó de destino.
     * @param length   O comprimento do caminho.
     * @param type     O tipo do caminho.
     * @return Um objeto Path representando o novo caminho criado.
     */
    @Query("MATCH(s),(t) " +
            "WHERE ID(s) = $sourceId AND ID(t) = $targetId " +
            "MERGE (s)-[r:`TUBULAÇÃO` {length: $length, type: $type}]->(t) " +
            "RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type")
    Path createPath(@Param("sourceId") long sourceId, @Param("targetId") long targetId, @Param("length") long length, @Param("type") String type);

    /**
     * Remove um caminho entre dois nós com os IDs fornecidos.
     *
     * @param sourceId O ID do nó de origem.
     * @param targetId O ID do nó de destino.
     */
    @Query("MATCH (s)-[r]->(t) WHERE ID(s) = $sourceId AND ID(t) = $targetId DELETE r")
    void removePath(@Param("sourceId") long sourceId, @Param("targetId") long targetId);

    @Query(
        "MATCH (s)-[r:`TUBULAÇÃO`]-(t)\n" + //
        "where r.type IN [$restriction1, $restriction2] or properties(r) = {}\n" + //
        "RETURN ID(r) AS id, ID(s) AS sourceId, ID(t) AS targetId, r.length AS length, r.type AS type"
    )
    List<Path> fetchPathWithRestriction(@Param("restriction1") String restriction1, @Param("restriction2")  String restriction2);

}
