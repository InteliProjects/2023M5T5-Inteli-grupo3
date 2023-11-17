/**
 * Esta classe representa as propriedades de relacionamento de uma aresta (path) em um grafo.
 */
package com.inteli.onebeertwo.models;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

/**
 * Classe que representa as propriedades de relacionamento de uma aresta (path).
 */
@RelationshipProperties
public class Path {

    /**
     * Identificador do relacionamento.
     */
    @RelationshipId
    private long neoId;

    /**
     * Identificador único deste Path.
     */
    private long id;

    /**
     * Identificador do nó de destino do relacionamento (aresta).
     */
    @Property
    private long targetId;

    /**
     * Identificador do nó de origem do relacionamento (aresta).
     */
    @Property
    private long sourceId;

    /**
     * Comprimento do Path (aresta).
     */
    @Property
    private long length;

    /**
     * Tipo do Path (aresta).
     */
    @Property
    private String type;

    /**
     * Construtor vazio padrão.
     */
    public Path() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param neoId    O identificador do relacionamento.
     * @param id       O identificador único deste Path.
     * @param targetId O identificador do nó de destino do relacionamento.
     * @param sourceId O identificador do nó de origem do relacionamento.
     * @param length   O comprimento do Path.
     * @param type     O tipo do Path.
     */
    public Path(long neoId, long id, long targetId, long sourceId, long length, String type) {
        this.neoId = neoId;
        this.id = id;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.length = length;
        this.type = type;
    }

    /**
     * Obtém o ID único deste Path.
     *
     * @return O ID único deste Path.
     */
    public long getId() {
        return id;
    }

    /**
     * Define o ID único deste Path.
     *
     * @param id O ID único deste Path.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtém o ID do nó de destino do relacionamento.
     *
     * @return O ID do nó de destino do relacionamento.
     */
    public long getTargetId() {
        return targetId;
    }

    /**
     * Define o ID do nó de destino do relacionamento.
     *
     * @param targetId O ID do nó de destino do relacionamento.
     */
    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    /**
     * Obtém o ID do nó de origem do relacionamento.
     *
     * @return O ID do nó de origem do relacionamento.
     */
    public long getSourceId() {
        return sourceId;
    }

    /**
     * Obtém o comprimento do Path.
     *
     * @return O comprimento do Path.
     */
    public long getLength() {
        return length;
    }

    /**
     * Define o comprimento do Path.
     *
     * @param length O comprimento do Path.
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Obtém o tipo do Path.
     *
     * @return O tipo do Path.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo do Path.
     *
     * @param type O tipo do Path.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Define o ID do nó de origem do relacionamento.
     *
     * @param sourceId O ID do nó de origem do relacionamento.
     */
    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }
}
