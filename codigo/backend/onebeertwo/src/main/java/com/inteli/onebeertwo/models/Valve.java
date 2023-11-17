/**
 * Esta classe representa as válvulas em um grafo.
 */
package com.inteli.onebeertwo.models;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * Classe que representa as válvulas no grafo.
 */
@Node("VALVULA")
public class Valve {

    /**
     * Atributo para armazenar o ID da válvula.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Atributo para armazenar o nome da válvula.
     */
    private String name;

    /**
     * Atributo para armazenar o tipo da válvula.
     */
    private String type;

    /** 
     * Lista de relacionamento com válvulas INOUT usando o tipo de relacionamento "TUBULAÇÃO"
     */
    @Relationship(type = "TUBULAÇÃO", direction = Relationship.Direction.OUTGOING)
    private List<INOUT> connectedINOUT;
    
    /**
     * Construtor vazio padrão.
     */
    public Valve() {
        // Construtor Vazio
    }

    
    /**
     * @param name O nome da válvula.
     * @param type O tipo da válvula.
     * @param connectedINOUT A lista de InOuts associadas as válvulas.
     */
    public Valve(String name, String type, List<INOUT> connectedINOUT) {
        this.name = name;
        this.type = type;
        this.connectedINOUT = connectedINOUT;
    }

    
    /**
     * Getter para obter o ID da válvula
     * 
     * @return O ID da Válvula
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter para definir o ID da válvula
     * 
     * @param id O ID da válvula
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter para obter o nome da válvula
     * 
     * @return O nome da válvula
     */
    public String getName() {
        return name;
    }

    /**
     * Setter para definir o nome da válvula
     * 
     * @param name O nome da válvula
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter para obter o tipo da válvula
     * 
     * @return O tipo da válvula
     */
    public String getType() {
        return type;
    }

    /**
     * Setter para definir o tipo da válvula
     * 
     * @param type O tipo da válvula
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * Getter para obter a lista de válvulas inout associadas as válvulas
     * 
     * @return A lista de InOuts conectadas
     */
    public List<INOUT> getConnectedINOUT() {
        return connectedINOUT;
    }

    /**
     * Setter para definir a lista de válvulas inout associadas as válvulas
     * 
     * @param connectedINOUT A lista de InOuts conectadas
     */
    public void setConnectedINOUT(List<INOUT> connectedINOUT) {
        this.connectedINOUT = connectedINOUT;
    }

    
}
