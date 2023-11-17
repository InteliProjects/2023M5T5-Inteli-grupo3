package com.inteli.onebeertwo.objects;

/**
 * Classe de objeto para transferência de dados (DTO) relacionada a um tanque.
 */
public class TankDTO {
    
    /**
     * Atributo para armazenar o nome do tanque.
     */
    private String name;
    
    /**
     * Atributo para armazenar o volume do tanque.
     */
    private Double volume;

    /**
     * Construtor com parâmetros para definir o nome e o volume do tanque.
     *
     * @param name   O nome a ser atribuído ao tanque.
     * @param volume O volume a ser atribuído ao tanque.
     */
    public TankDTO(String name, Double volume) {
        this.name = name;
        this.volume = volume;
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
     * @param name O novo nome a ser definido para o tanque.
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
     * @param volume O novo volume a ser definido para o tanque.
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
