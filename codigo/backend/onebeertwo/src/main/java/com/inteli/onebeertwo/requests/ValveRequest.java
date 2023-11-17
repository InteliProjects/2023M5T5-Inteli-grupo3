package com.inteli.onebeertwo.requests;

/**
 * Classe de objeto para requisições relacionadas a Válvulas.
 */
public class ValveRequest {
    
    /**
     * Atributo para armazenar o nome relacionado a uma Válvula.
     */
    private String name;
    
    /**
     * Atributo para armazenar o tipo relacionado a uma Válvula.
     */
    private String type;

    /**
     * Construtor vazio padrão.
     */
    public ValveRequest() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param name O nome relacionado a uma Válvula.
     * @param type O tipo relacionado a uma Válvula.
     */
    public ValveRequest(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Obtém o nome relacionado a uma Válvula.
     *
     * @return O nome relacionado a uma Válvula.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome relacionado a uma Válvula.
     *
     * @param name O novo nome a ser definido para a Válvula.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o tipo relacionado a uma Válvula.
     *
     * @return O tipo relacionado a uma Válvula.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo relacionado a uma Válvula.
     *
     * @param type O novo tipo a ser definido para a Válvula.
     */
    public void setType(String type) {
        this.type = type;
    }
}
