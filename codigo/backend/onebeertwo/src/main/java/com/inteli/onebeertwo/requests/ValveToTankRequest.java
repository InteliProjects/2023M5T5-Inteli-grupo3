package com.inteli.onebeertwo.requests;

/**
 * Classe de objeto para requisições de associação entre Válvula e Tanque.
 */
public class ValveToTankRequest {
    
    /**
     * Atributo para armazenar o nome da Válvula.
     */
    private String valveName;
    
    /**
     * Atributo para armazenar o nome do Tanque.
     */
    private String tankName;
    
    /**
     * Construtor vazio padrão.
     */
    public ValveToTankRequest() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param valveName O nome da Válvula.
     * @param tankName  O nome do Tanque.
     */
    public ValveToTankRequest(String valveName, String tankName) {
        this.valveName = valveName;
        this.tankName = tankName;
    }

    /**
     * Obtém o nome da Válvula.
     *
     * @return O nome da Válvula.
     */
    public String getValveName() {
        return valveName;
    }

    /**
     * Define o nome da Válvula.
     *
     * @param valveName O novo nome a ser definido para a Válvula.
     */
    public void setValveName(String valveName) {
        this.valveName = valveName;
    }

    /**
     * Obtém o nome do Tanque.
     *
     * @return O nome do Tanque.
     */
    public String getTankName() {
        return tankName;
    }

    /**
     * Define o nome do Tanque.
     *
     * @param tankName O novo nome a ser definido para o Tanque.
     */
    public void setTankName(String tankName) {
        this.tankName = tankName;
    }
}
