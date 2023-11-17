package com.inteli.onebeertwo.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.inteli.onebeertwo.models.Tank;

/**
 * Interface que define um repositório para interagir com os nós do tipo Tanque no grafo Neo4j.
 */
public interface TankRepository extends Neo4jRepository<Tank, Long> {

    /**
     * Método para buscar um nó do tipo Tanque pelo nome.
     *
     * @param name O nome do nó Tanque a ser procurado.
     * @return Um objeto Optional contendo o nó Tanque correspondente, se encontrado.
     */
    Optional<Tank> findTankByName(String name);
    
    /**
     * Método para buscar um nó do tipo Tanque pelo ID.
     *
     * @param id O ID do nó Tanque a ser procurado.
     * @return Um objeto Optional contendo o nó Tanque correspondente, se encontrado.
     */
    Optional<Tank> findTankById(Long id);
}
