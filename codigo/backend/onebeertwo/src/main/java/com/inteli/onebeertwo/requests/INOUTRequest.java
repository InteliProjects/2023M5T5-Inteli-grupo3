package com.inteli.onebeertwo.requests;

/**
 * Classe de objeto para requisições relacionadas à válvula INOUT.
 */
public class INOUTRequest {
    
    /**
     * Atributo para armazenar o nome relacionado à válvula INOUT.
     */
    private String name;

    /**
     * Construtor vazio padrão.
     */
    public INOUTRequest() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param name O nome relacionado à válvula INOUT.
     */
    public INOUTRequest(String name) {
        this.name = name;
    }

    /**
     * Obtém o nome relacionado à válvula INOUT.
     *
     * @return O nome relacionado à válvula INOUT.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome relacionado à válvula INOUT.
     *
     * @param name O novo nome a ser definido para a válvula INOUT.
     */
    public void setName(String name) {
        this.name = name;
    }
}
