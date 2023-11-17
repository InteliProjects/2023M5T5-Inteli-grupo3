package com.inteli.onebeertwo.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.inteli.onebeertwo.services.CsvProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Controlador para lidar com o upload de arquivos CSV.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/csv")
public class UploadController {

    /**
     * Injeção de dependência do CsvProcessor.
     */
    @Autowired
    private CsvProcessor csvProcessor; 

    /**
     * Rota para entrada do CSV de criação dos nodos TANQUE
     * 
     * @param file O arquivo CSV a ser enviado.
     * @param projectName O nome do projeto.
     * @return ResponseEntity com uma mensagem indicando o resultado do upload.
     */
    @PostMapping("/uploadTanque")
    public ResponseEntity<String> uploadCSVTanque(@RequestParam("file") MultipartFile file, @RequestParam("projectName") String projectName) {
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                csvProcessor.processTanqueCSV(file.getInputStream(), projectName);
                return ResponseEntity.ok("Upload de CSV bem-sucedido e tanques criados com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }
    /**
     * Rota para entrada do CSV de criação dos nodos VALVULA
     * 
     * @param file O arquivo CSV a ser enviado.
     * @param projectName O nome do projeto.
     * @return ResponseEntity com uma mensagem indicando o resultado do upload.
     */
    @PostMapping("/uploadValvula")
    public ResponseEntity<String> uploadCSVValvula(@RequestParam("file") MultipartFile file, @RequestParam("projectName") String projectName) {
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                csvProcessor.processValvulaCSV(file.getInputStream(), projectName);
                return ResponseEntity.ok("Upload de CSV bem-sucedido e válvulas criadas com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }


    /**
     * Rota para entrada do CSV de criação dos nodos INOUT
     * 
     * @param file O arquivo csv a ser enviado.
     * @param projectName O nome do projeto.
     * @return ResponseEntity com uma mensagem indicando o resultado do upload.
     */
    @PostMapping("/uploadInout")
    public ResponseEntity<String> uploadCSVInout(@RequestParam("file") MultipartFile file, @RequestParam("projectName") String projectName) {
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                csvProcessor.processInoutCSV(file.getInputStream(), projectName);
                return ResponseEntity.ok("Upload de CSV bem-sucedido e inouts criadas com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }

    /**
     * Rota para entrada do CSV de criação dos relacionamentos TUBULAÇÃO 
     * 
     * @param file O arquivo CSV a ser enviado.
     * @param projectName O nome do projeto.
     * @return ResponseEntity com uma mensagem indicando o resultado do upload.
     */
    @PostMapping("/uploadRelation")
    public ResponseEntity<String> uploadCSVRelation(@RequestParam("file") MultipartFile file, @RequestParam("projectName") String projectName) {
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                csvProcessor.processRelationCSV(file.getInputStream(), projectName);
                return ResponseEntity.ok("Upload de CSV bem-sucedido e arestas criadas com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }
}
