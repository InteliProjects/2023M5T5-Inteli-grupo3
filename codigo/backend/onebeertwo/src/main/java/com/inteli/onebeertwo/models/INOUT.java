/**
 * Esta classe representa uma válvula INOUT em um sistema.
 */
package com.inteli.onebeertwo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

/**
 * Classe que representa a válvula INOUT.
 */
public class INOUT {
    
    /** 
     * Atributo para armazenar o ID da válvula INOUT.
     */
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     * Atributo para armazenar o nome da válvula INOUT.
     */
    private String name;

    /**
     * Construtor padrão vazio.
     */
    public INOUT() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param id   O ID da válvula INOUT.
     * @param name O nome da válvula INOUT.
     */
    public INOUT(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Obtém o ID da válvula INOUT.
     *
     * @return O ID da válvula INOUT.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da válvula INOUT.
     *
     * @param id O ID da válvula INOUT.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da válvula INOUT.
     *
     * @return O nome da válvula INOUT.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da válvula INOUT.
     *
     * @param name O nome da válvula INOUT.
     */
    public void setName(String name) {
        this.name = name;
    }
}
