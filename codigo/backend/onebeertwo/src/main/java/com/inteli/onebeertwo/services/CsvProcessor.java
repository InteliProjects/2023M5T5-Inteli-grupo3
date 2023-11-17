package com.inteli.onebeertwo.services;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Um serviço para processar arquivos CSV e criar nós e relacionamentos no Neo4j.
 */
@Service
public class CsvProcessor {

    /**
     * O driver Neo4j utilizado para interagir com o banco de dados Neo4j.
     */
    private final Driver neo4jDriver;

    /**
     * Construtor que recebe o driver Neo4j como parâmetro.
     *
     * @param neo4jDriver O driver Neo4j que será utilizado para interagir com o banco de dados.
     */
    public CsvProcessor(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
    }

    /**
     * Processa um arquivo CSV, criando nós no banco de dados Neo4j com base nos dados do CSV.
     * Conecta os nodos de label TANQUE no nodo do projeto recebido.
     * 
     * @param inputStream O fluxo de entrada que contém os dados do arquivo CSV a ser processado.
     */
    public void processTanqueCSV(InputStream inputStream, String projectName) {
        try (Session session = neo4jDriver.session()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            boolean firstLine = true;
            List<String> csvData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                csvData.add(line);
            }

            for (String csvLine : csvData) {
                String[] data = csvLine.split(";");
                String name = data[0];
                String volumeStr = data[1];

                double volume = Double.parseDouble(volumeStr);

                Map<String, Object> nodeProps = new HashMap<>();
                nodeProps.put("name", name);
                nodeProps.put("volume", volume);
                nodeProps.put("nameProject", projectName);

                Map<String, Object> projectProps = new HashMap<>();
                projectProps.put("nameProject", projectName);
                projectProps.put("name", name);

                // Cria o nodo de label TANQUE com suas propriedades
                session.run("MERGE (node1:TANQUE {name: $name, volume: $volume, project: $nameProject})", nodeProps);

                // Cria o nodo do projeto se ele não existir, e conecta todos os nodos de label TANQUE que foram criados no nodo do projeto
                session.run("MERGE (project:PROJECT {name: $nameProject})", projectProps);
                session.run("MATCH (project:PROJECT {name: $nameProject}), (node1:TANQUE {name: $name, project: $nameProject}) MERGE (project)-[:CONTAINS_NODE]->(node1)", projectProps);

                System.out.println("Node criado: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Processa os dados do CSV de valvulas para criação dos nodos valvulas no Neo4J.
     * Conecta os nodos de labeç VALVULA com o nodo do projeto recebido
     * 
     * @param inputStream Fluxo entrada de dados do CSV de valvulas para ser processo
     */
    public void processValvulaCSV(InputStream inputStream, String projectName) {
        try (Session session = neo4jDriver.session()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            boolean firstLine = true;
            List<String> csvData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;

                    continue;
                }
                csvData.add(line);
            }

            for (String csvLine : csvData) {
                String[] data = csvLine.split(";");
                String name = data[0];
                String type = data[1];

                Map<String, Object> nodeProps = new HashMap<>();
                nodeProps.put("name", name);
                nodeProps.put("type", type);
                nodeProps.put("nameProject", projectName);

                Map<String, Object> projectProps = new HashMap<>();
                projectProps.put("nameProject", projectName);
                projectProps.put("name", name);

                // Cria o nodo de label VALVULA com suas propriedades
                session.run("MERGE (node1:VALVULA {name: $name, type: $type, project: $nameProject})", nodeProps);
                
                // Cria o nodo do projeto se ele não existir, e conecta todos os nodos de label VALVULA que foram criados no nodo do projeto
                session.run("MERGE (project:PROJECT {name: $nameProject})", projectProps);
                session.run("MATCH (project:PROJECT {name: $nameProject}), (node1:VALVULA {name: $name, project: $nameProject}) MERGE (project)-[:CONTAINS_NODE]->(node1)", projectProps);
                
                System.out.println("Node criado: " + name);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processa os dados de Inout para criação dos nodos Inout no Neo4J.
     * Conecta os nodos inout ao nodo projeto recebido
     * 
     * @param inputStream Entrada que contém os dados do arquivo CSV
     */
    public void processInoutCSV(InputStream inputStream, String projectName) {
        try (Session session = neo4jDriver.session()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            boolean firstLine = true;
            List<String> csvData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                csvData.add(line);
            }
            
            for (String csvLine : csvData) {
                String[] data = csvLine.split(";");
                String name = data[0];
                String type = data[1];

                Map<String, Object> nodeProps = new HashMap<>();
                nodeProps.put("name", name);
                nodeProps.put("type", type);
                nodeProps.put("nameProject", projectName);

                Map<String, Object> projectProps = new HashMap<>();
                projectProps.put("nameProject", projectName);
                projectProps.put("name", name);

                // Cria o nodo de label INOUT com suas propriedades
                session.run("MERGE (node1:INOUT {name: $name, type: $type, project: $nameProject})", nodeProps);

                // Cria o nodo do projeto se ele não existir, e conecta todos os nodos de label INOUT que foram criados no nodo do projeto
                session.run("MERGE (project:PROJECT {name: $nameProject})", projectProps);
                session.run("MATCH (project:PROJECT {name: $nameProject}), (node1:INOUT {name: $name, project: $nameProject}) MERGE (project)-[:CONTAINS_NODE]->(node1)", projectProps);
                
                System.out.println("Node criado: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processa um arquivo CSV, criando relacionamentos entre nós no banco de dados Neo4j com base nos dados do CSV.
     *
     * @param inputStream O fluxo de entrada que contém os dados do arquivo CSV a ser processado.
     */
    public void processRelationCSV(InputStream inputStream, String projectName) {
        try (Session session = neo4jDriver.session()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            boolean firstLine = true;
            List<String> csvData = new ArrayList<>();
    
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                csvData.add(line);
            }
    
            for (String csvLine : csvData) {
                String[] data = csvLine.split(";");
                String type_source = data[2];
                String source = data[3];
                String type_target = data[0];
                String target = data[1];
                String type = data[4];
                String lengthStr = data[5];
    
                double length = Double.parseDouble(lengthStr);
    
                Map<String, Object> nodeProps = new HashMap<>();
                nodeProps.put("source", source);
                nodeProps.put("target", target);
                nodeProps.put("type", type);
                nodeProps.put("length", length);
                nodeProps.put("nameProject", projectName);
    
                // Crie o relacionamento entre os nós
                session.run("MATCH (n1:" + type_source + " {name: $source, project: $nameProject}), (n2:" + type_target + " {name: $target, project: $nameProject}) MERGE (n1)<-[:TUBULAÇÃO {type: $type, length: $length}]-(n2)", nodeProps);
                            
                System.out.println("Relacionamento de"+ source + "criado para " + target);
                
            }
        } catch (Exception e) {
            // Lida com exceções de maneira apropriada, como registro ou relatório de erros.
            e.printStackTrace();
        }
    }
    
}
