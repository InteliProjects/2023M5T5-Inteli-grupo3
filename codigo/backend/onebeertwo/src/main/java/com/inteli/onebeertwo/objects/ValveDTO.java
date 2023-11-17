package com.inteli.onebeertwo.objects;

/**
 * Classe de objeto para transferência de dados (DTO) relacionada a uma válvula.
 */
public class ValveDTO {
    
    /**
     * Atributo para armazenar o nome da válvula.
     */
    private String name;
    
    /**
     * Atributo para armazenar o tipo da válvula.
     */
    private String type;

    /**
     * Construtor com parâmetros para definir o nome e o tipo da válvula.
     *
     * @param name O nome a ser atribuído à válvula.
     * @param type O tipo a ser atribuído à válvula.
     */
    public ValveDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Obtém o nome da válvula.
     *
     * @return O nome da válvula.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da válvula.
     *
     * @param name O novo nome a ser definido para a válvula.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o tipo da válvula.
     *
     * @return O tipo da válvula.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo da válvula.
     *
     * @param type O novo tipo a ser definido para a válvula.
     */
    public void setType(String type) {
        this.type = type;
    }
}
