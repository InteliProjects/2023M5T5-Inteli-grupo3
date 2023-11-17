/**
 * Esta classe representa os tanques em um grafo.
 */
package com.inteli.onebeertwo.models;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * Classe que representa os tanques no grafo.
 */
@Node("TANQUE")
public class Tank {

    /**
     * Atributo para armazenar o ID do tanque.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Atributo para armazenar o nome do tanque.
     */
    private String name;

    /**
     * Atributo para armazenar o volume do tanque.
     */
    private Double volume;

    /**
     * Lista de relacionamento com válvulas usando o tipo de relacionamento "TUBULAÇÃO".
     */
    @Relationship(type = "TUBULAÇÃO", direction = Relationship.Direction.OUTGOING)
    private List<Valve> valves;

    /**
     * Construtor vazio padrão.
     */
    public Tank() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param name   O nome do tanque.
     * @param volume O volume do tanque.
     * @param valves A lista de válvulas associadas ao tanque.
     */
    public Tank(String name, Double volume, List<Valve> valves) {
        this.name = name;
        this.volume = volume;
        this.valves = valves;
    }

    /**
     * Obtém o ID do tanque.
     *
     * @return O ID do tanque.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do tanque.
     *
     * @param id O ID do tanque.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do tanque.
     *
     * @return O nome do tanque.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do tanque.
     *
     * @param name O nome do tanque.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o volume do tanque.
     *
     * @return O volume do tanque.
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * Define o volume do tanque.
     *
     * @param volume O volume do tanque.
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    /**
     * Obtém a lista de válvulas associadas ao tanque.
     *
     * @return A lista de válvulas associadas ao tanque.
     */
    public List<Valve> getValves() {
        return valves;
    }

    /**
     * Define a lista de válvulas associadas ao tanque.
     *
     * @param valves A lista de válvulas associadas ao tanque.
     */
    public void setValves(List<Valve> valves) {
        this.valves = valves;
    }
}
