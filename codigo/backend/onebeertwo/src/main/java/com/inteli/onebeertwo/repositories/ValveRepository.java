package com.inteli.onebeertwo.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.inteli.onebeertwo.models.Valve;

/**
 * Interface que define um repositório para interagir com os nós do tipo Válvula no grafo Neo4j.
 */
public interface ValveRepository extends Neo4jRepository<Valve, Long> {

    /**
     * Método para buscar um nó do tipo Válvula pelo nome.
     *
     * @param name O nome do nó Válvula a ser procurado.
     * @return Um objeto Optional contendo o nó Válvula correspondente, se encontrado.
     */
    Optional<Valve> findValveByName(String name);
    
    /**
     * Método para buscar um nó do tipo Válvula pelo ID.
     *
     * @param id O ID do nó Válvula a ser procurado.
     * @return Um objeto Optional contendo o nó Válvula correspondente, se encontrado.
     */
    Optional<Valve> findValveById(Long id);
    
    /**
     * Consulta para verificar se existe uma relação de uma Válvula para um Tanque.
     *
     * @param valvulaName O nome da válvula.
     * @param tankName    O nome do tanque.
     * @return Verdadeiro se a relação existe, falso caso contrário.
     */
    @Query("MATCH (v:VALVULA), (t:TANQUE) WHERE v.name = $valvulaName AND t.name = $tankName " +
    "RETURN EXISTS((v)-[:TUBULAÇÃO]->(t))")
    Boolean checkRelationshipValveTank(String valvulaName, String tankName);

    /**
     * Consulta para criar uma relação de uma Válvula para um Tanque.
     *
     * @param valvulaName O nome da válvula.
     * @param tankName    O nome do tanque.
     */
    @Query("MATCH (v:VALVULA), (t:TANQUE) WHERE v.name = $valvulaName AND t.name = $tankName " +
    "CREATE (v)-[:TUBULAÇÃO]->(t)")
    void createRelationshipValveTank(String valvulaName, String tankName);
}
