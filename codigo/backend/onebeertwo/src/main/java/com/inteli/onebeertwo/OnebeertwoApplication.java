package com.inteli.onebeertwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
// <<<<<<< backendEstruturadeDados


// main

// Classe principal que inicia a aplicação Spring Boot

@SpringBootApplication
@CrossOrigin
public class OnebeertwoApplication {

    // Método principal que inicia a execução da aplicação
    public static void main(String[] args) {
        SpringApplication.run(OnebeertwoApplication.class, args);
    }

}