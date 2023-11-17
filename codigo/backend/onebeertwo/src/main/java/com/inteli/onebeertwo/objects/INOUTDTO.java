package com.inteli.onebeertwo.objects;

/**
 * Classe de objeto para transferência de dados (DTO) relacionada a INOUT.
 */
public class INOUTDTO {
    
    /**
     * Atributo para armazenar o nome.
     */
    private String name;

    /**
     * Construtor vazio padrão.
     */
    public INOUTDTO() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param name O nome a ser atribuído ao objeto.
     */
    public INOUTDTO(String name) {
        this.name = name;
    }

    /**
     * Obtém o nome atual do objeto.
     *
     * @return O nome atual do objeto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do objeto.
     *
     * @param name O novo nome a ser definido para o objeto.
     */
    public void setName(String name) {
        this.name = name;
    }
}
