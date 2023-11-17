package com.inteli.onebeertwo.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inteli.onebeertwo.repositories.PathRepository;
import com.inteli.onebeertwo.services.INOUTService;
import com.inteli.onebeertwo.services.TankService;
import com.inteli.onebeertwo.services.ValveService;

/**
 * Controller responsável por processar caminhos e exportar dados relacionados em um arquivo CSV.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ExportController {

    @Autowired
    private ValveService valveService;

    @Autowired
    private TankService tankService;

    @Autowired
    private INOUTService inoutService;

    @Autowired
    private PathRepository tubulationRepository;

    /**
     * Processa os caminhos fornecidos e imprime os resultados no console.
     * 
     * @param pathsData Uma lista de listas, representando os caminhos a serem processados.
     * @return Uma ResponseEntity contendo uma mensagem sobre o sucesso ou falha do processamento.
     */
    @PostMapping("/processPaths")
    public ResponseEntity<?> processPaths(@RequestBody List<List<Long>> pathsData) {
        try {
            Map<Long, String> valveIdToNameMap = valveService.getValveIdToNameMap();
            Map<Long, String> tankIdToNameMap = tankService.getTankIdToNameMap();
            Map<Long, String> inoutIdToNameMap = inoutService.getInoutIdToNameMap();

            List<String> header = new ArrayList<>();
            List<List<String>> rows = new ArrayList<>();
            List<List<String>> testes = new ArrayList<>();

            // Constroi o cabeçalho da planilha
            header.add("Caminho");

            // Preenche os mapas conforme necessário
            valveService.populateValveIdToNameMap();
            tankService.populateTankIdToNameMap();
            inoutService.populateInoutIdToNameMap();

            // Adiciona os nomes dos nós presentes nos caminhos ao cabeçalho
            for (List<Long> path : pathsData) {
                for (Long nodeId : path) {
                    String nodeName = null;
                    if (valveIdToNameMap.containsKey(nodeId)) {
                        nodeName = valveIdToNameMap.get(nodeId);
                    } else if (tankIdToNameMap.containsKey(nodeId)) {
                        nodeName = tankIdToNameMap.get(nodeId);
                    } else if (inoutIdToNameMap.containsKey(nodeId)) {
                        nodeName = inoutIdToNameMap.get(nodeId);

                    }

                    if (nodeName != null && !header.contains(nodeName)) {
                        header.add(nodeName);
                    }
                }
            }

            // Adiciona o cabeçalho à lista de linhas
            rows.add(header);

            // Itera sobre os dados do caminho
            for (int i = 0; i < pathsData.size(); i++) {
                List<Long> path = pathsData.get(i);
                List<String> row = new ArrayList<>();
                row.add("Caminho " + (i + 1));

                for (int j = 0; j < path.size(); j++) {
                    Long id = path.get(j);

                    if (valveService.isValve(id.longValue())) {
                        String valveType = valveService.getValveType(id.longValue());

                        if (valveType == null) {
                            row.add(" "); // se valve type for nulo o csv vai praticamente retornar em branco
                        } else if (valveType.equals("mixproof")) {
                            // Verifica os tipos de tubulação se não for a primeira válvula no caminho
                            if (j > 0 && j < path.size() - 1) {
                                String typeTubulationIn = tubulationRepository.getTubulationType(path.get(j - 1), id);
                                String typeTubulationOut = tubulationRepository.getTubulationType(id, path.get(j + 1));

                                // Compare os tipos de tubulação
                                if (typeTubulationIn != null && typeTubulationOut != null) {
                                    if (typeTubulationIn.equals(typeTubulationOut)){
                                        row.add(valveIdToNameMap.get(id) + ":" + "0"); // 0 = não energizado
                                    } else {
                                        row.add(valveIdToNameMap.get(id) + ":" + "1"); // 1 = energizado
                                    }
                                } else {
                                    row.add(valveIdToNameMap.get(id)+ ":" + " ");
                                }
                            } else {
                                row.add(valveIdToNameMap.get(id) + ":" + " ");
                            }
                        } else if (valveType.equals("solenoide")) {
                            row.add(valveIdToNameMap.get(id)+ ":" + "1"); // 1 = energizado
                        }
                    } else if (tankService.isTank(id.longValue())) {
                        row.add(tankIdToNameMap.get(id)+ ":" + "1");  // 1 = energizado
                    } else if (inoutService.isInout(id.longValue())) {
                        row.add(inoutIdToNameMap.get(id)+ ":" + "1");  // 1 = energizado
                    } else {
                        row.add(" ");
                    }
                }

                // Adiciona a linha à lista de linhas
                testes.add(row);
            }
            System.out.println(rows);
            System.out.println(testes);

            gerarCSV(header, testes);

            return ResponseEntity.ok("Processamento concluído com sucesso. Dados impressos no console.");
            

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar os caminhos.");
        }
    }

    /**
     * Gera um arquivo CSV com base nos cabeçalhos e caminhos fornecidos.
     * 
     * @param header Uma lista contendo os cabeçalhos para o arquivo CSV.
     * @param caminhos Uma lista de listas, representando os caminhos a serem escritos no arquivo CSV.
     */
    private static void gerarCSV(List<String> header, List<List<String>> caminhos) {
        try (FileWriter writer = new FileWriter("codigo/frontend/public/output.csv")) {
            // Escreve o cabeçalho
            writer.append(String.join(";", header));
            writer.append("\n");
            int i = 0;
            // Escreve os caminhos
            for (List<String> caminho : caminhos) {
                List<String> linha = new ArrayList<>();
                boolean first = true;
                // Preencha a linha com os valores correspondentes aos headers
                for (String item : header) {
                    if (first) {
                        i++;
                        first = false;
                        linha.add("Caminho" + i);
                    } else {
                        // Acha a correspondência em caminho que começa com item + ":"
                        String matchedValue = caminho.stream()
                                                     .filter(s -> s.startsWith(item + ":"))
                                                     .findFirst()
                                                     .orElse(null);
                        if (matchedValue != null) {
                            String[] parts = matchedValue.split(":");
                            linha.add(parts[1].trim()); // Adiciona a segunda parte da divisão
                        } else {
                            linha.add(""); // Deixa em branco se não estiver presente no caminho
                        }
                    }
                }

                writer.append(String.join(";", linha));
                writer.append("\n");
            }

            System.out.println("CSV gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

