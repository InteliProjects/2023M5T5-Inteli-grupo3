package com.inteli.onebeertwo.objects;

/**
 * DTO (Data Transfer Object) que representa informações simplificadas de um projeto.
 */
public class PROJECTDTO {
  
    /**
     * Nome do projeto.
     */
    private String name;

    /**
     * Construtor padrão.
     */
    public PROJECTDTO() {
    }

    /**
     * Construtor que inicializa o DTO com um nome de projeto.
     *
     * @param name O nome do projeto.
     */
    public PROJECTDTO(String name) {
        this.name = name;
    }

    /**
     * Obtém o nome do projeto.
     *
     * @return O nome do projeto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do projeto.
     *
     * @param name O novo nome do projeto.
     */
    public void setName(String name) { 
        this.name = name;
    }
}
