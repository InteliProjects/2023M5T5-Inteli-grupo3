package com.inteli.onebeertwo.requests;

/**
 * Classe de objeto para requisições relacionadas a Tanques.
 */
public class TankRequest {
    
    /**
     * Atributo para armazenar o nome relacionado a um Tanque.
     */
    private String name;
    
    /**
     * Atributo para armazenar o volume relacionado a um Tanque.
     */
    private Double volume;

    /**
     * Construtor vazio padrão.
     */
    public TankRequest() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param name   O nome relacionado a um Tanque.
     * @param volume O volume relacionado a um Tanque.
     */
    public TankRequest(String name, Double volume) {
        this.name = name;
        this.volume = volume;
    }

    /**
     * Obtém o nome relacionado a um Tanque.
     *
     * @return O nome relacionado a um Tanque.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome relacionado a um Tanque.
     *
     * @param name O novo nome a ser definido para o Tanque.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o volume relacionado a um Tanque.
     *
     * @return O volume relacionado a um Tanque.
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * Define o volume relacionado a um Tanque.
     *
     * @param volume O novo volume a ser definido para o Tanque.
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
