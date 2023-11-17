package com.inteli.onebeertwo.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.inteli.onebeertwo.models.INOUT;

/**
 * Interface que define um repositório para interagir com os nós do tipo INOUT no grafo Neo4j.
 */
public interface INOUTRepository extends Neo4jRepository<INOUT, Long> {
    
    /**
     * Método para buscar um nó do tipo INOUT pelo nome.
     *
     * @param name O nome do nó INOUT a ser procurado.
     * @return Um objeto Optional contendo o nó INOUT correspondente, se encontrado.
     */
    Optional<INOUT> findINOUTByName(String name);
    
    /**
     * Método para buscar um nó do tipo INOUT pelo ID.
     *
     * @param id O ID do nó INOUT a ser procurado.
     * @return Um objeto Optional contendo o nó INOUT correspondente, se encontrado.
     */
    Optional<INOUT> findINOUTById(Long id);
}
