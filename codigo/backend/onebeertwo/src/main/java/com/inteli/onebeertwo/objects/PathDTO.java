package com.inteli.onebeertwo.objects;

/**
 * Classe de objeto para transferência de dados (DTO) relacionado aos relacionamentos (path).
 */
public class PathDTO {

    /**
     * Atributo para armazenar o ID.
     */
    private long id;

    /**
     * Atributo para armazenar o ID do nó de destino.
     */
    private long targetId = -1;

    /**
     * Atributo para armazenar o ID do nó de origem.
     */
    private long sourceId = -1;

    /**
     * Atributo para armazenar o comprimento do path.
     */
    private long length = 0;

    /**
     * Atributo para armazenar o tipo do path.
     */
    private String type = "";

    /**
     * Construtor vazio padrão.
     */
    public PathDTO() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param id       O ID do path.
     * @param targetId O ID do nó de destino do path.
     * @param sourceId O ID do nó de origem do path.
     * @param length   O comprimento do path.
     * @param type     O tipo do path.
     */
    public PathDTO(long id, long targetId, long sourceId, long length, String type) {
        this.id = id;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.length = length;
        this.type = type;
    }

    /**
     * Obtém o ID do path.
     *
     * @return O ID do path.
     */
    public long getId() {
        return id;
    }

    /**
     * Define o ID do path.
     *
     * @param id O novo ID a ser definido para o path.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtém o ID do nó de destino do path.
     *
     * @return O ID do nó de destino do path.
     */
    public long getTargetId() {
        return targetId;
    }

    /**
     * Define o ID do nó de destino do path.
     *
     * @param targetId O novo ID do nó de destino a ser definido para o path.
     */
    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    /**
     * Obtém o ID do nó de origem do path.
     *
     * @return O ID do nó de origem do path.
     */
    public long getSourceId() {
        return sourceId;
    }

    /**
     * Define o ID do nó de origem do path.
     *
     * @param sourceId O novo ID do nó de origem a ser definido para o path.
     */
    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * Obtém o comprimento do path.
     *
     * @return O comprimento do path.
     */
    public long getLength() {
        return length;
    }

    /**
     * Define o comprimento do path.
     *
     * @param length O novo comprimento a ser definido para o path.
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Obtém o tipo do path.
     *
     * @return O tipo do path.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo do path.
     *
     * @param type O novo tipo a ser definido para o path.
     */
    public void setType(String type) {
        this.type = type;
    }
}
