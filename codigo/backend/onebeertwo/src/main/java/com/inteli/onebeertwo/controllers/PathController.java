package com.inteli.onebeertwo.controllers;

import com.inteli.onebeertwo.models.Path;
import com.inteli.onebeertwo.models.Grafo;
import com.inteli.onebeertwo.objects.PathDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.inteli.onebeertwo.repositories.PathRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Esta classe define os pontos de extremidade da API REST para gerenciar objetos Path (caminhos).
 */
@RestController
@RequestMapping("/api/paths")
@CrossOrigin
public class PathController {

    // Importando o repositorio para fazer as requisições
    private PathRepository pathRepository;

    public PathController(PathRepository pathRepository) {
        this.pathRepository = pathRepository;
    }

    /**
     * Retorna todos os caminhos presentes no grafo.
     *
     * @return ResponseEntity contendo uma lista de objetos Path e HttpStatus.OK
     */
    @GetMapping("/")
    public ResponseEntity<List<Path>> pathIndex() {
        List<Path> neighbors = pathRepository.fetchPaths();
        return new ResponseEntity<>(neighbors, HttpStatus.OK);
    }

    /**
     * Retorna todos os nós adjacentes de cada nó presente no grafo.
     *
     * @return ResponseEntity contendo um mapeamento de IDs de nó para listas de IDs de nós adjacentes e HttpStatus.OK
     */
    @GetMapping("/Adjacency")
    public ResponseEntity<Map<Long, List<Long>>> pathIndex2() {
        List<Path> neighbors = pathRepository.fetchPaths();

        Map<Long, List<Long>> dicionario = new HashMap<>();

        for (int i = 0; i < neighbors.size(); i++) {
            if (dicionario.containsKey(neighbors.get(i).getSourceId())) {
                // A chave já existe, então adicionamos o valor à lista existente
                dicionario.get(neighbors.get(i).getSourceId()).add(neighbors.get(i).getTargetId());
            } else {
                // A chave não existe, então criamos uma nova lista e adicionamos o valor
                List<Long> novaLista = new ArrayList<>();
                novaLista.add(neighbors.get(i).getTargetId());
                dicionario.put(neighbors.get(i).getSourceId(), novaLista);
            }
        }

        Grafo grafo = new Grafo();
        grafo.criarGrafo(dicionario);

        return new ResponseEntity<>(dicionario, HttpStatus.OK);

    }

    /**
     * Retorna o melhor caminho com base no algoritmo A*.
     *
     * @param begin O ID do nó de início.
     * @param end   O ID do nó de destino.
     * @return ResponseEntity contendo uma lista de IDs de nós representando o caminho e HttpStatus.OK
     */
    @GetMapping("/AStar")
    public ResponseEntity<List<Long>> pathIndexAStar(@RequestParam(name = "begin") Long begin, @RequestParam(name = "end") Long end, @RequestParam(name = "restriction1") String restriction1, @RequestParam(name = "restriction2") String restriction2) {
        List<Path> neighbors;

        // System.out.println("restriction1: " + restriction1);
        // System.out.println("restriction2: " + restriction2);

        if ((restriction1.equals("null") || restriction1.equals("undefined")) && (restriction2.equals("null") || restriction2.equals("undefined"))) {
            // System.out.println("Entrou no if");
            neighbors = pathRepository.fetchPaths();
        } else {
            neighbors = pathRepository.fetchPathWithRestriction(restriction1, restriction2);
            // System.out.println("Entrou no else");
        }

        Map<Long, List<Long>> dicionario = new HashMap<>();

        for (int i = 0; i < neighbors.size(); i++) {
            if (dicionario.containsKey(neighbors.get(i).getSourceId())) {
                // A chave já existe, então adicionamos o valor à lista existente
                dicionario.get(neighbors.get(i).getSourceId()).add(neighbors.get(i).getTargetId());
            } else {
                // A chave não existe, então criamos uma nova lista e adicionamos o valor
                List<Long> novaLista = new ArrayList<>();
                novaLista.add(neighbors.get(i).getTargetId());
                dicionario.put(neighbors.get(i).getSourceId(), novaLista);
            }
        }

        Grafo grafo = new Grafo();
        grafo.criarGrafoParaAStar(dicionario);

        return new ResponseEntity<>(grafo.encontrarCaminhoMinimoAStar(begin, end), HttpStatus.OK);
    }

    /**
     * Retorna o melhor caminho com base no algoritmo de Dijkstra.
     *
     * @param begin O ID do nó de início.
     * @param end   O ID do nó de destino.
     * @return ResponseEntity contendo uma lista de IDs de nós representando o caminho e HttpStatus.OK
     */
    @GetMapping("/Dijkstra")
    public ResponseEntity<List<Long>> pathIndex3(@RequestParam(name = "begin") Long begin, @RequestParam(name = "end") Long end, @RequestParam(name = "restriction1") String restriction1, @RequestParam(name = "restriction2") String restriction2) {

        List<Path> neighbors;

        // System.out.println("restriction1: " + restriction1);
        // System.out.println("restriction2: " + restriction2);

        if ((restriction1.equals("null") || restriction1.equals("undefined")) && (restriction2.equals("null") || restriction2.equals("undefined"))) {
            // System.out.println("Entrou no if");
            neighbors = pathRepository.fetchPaths();
        } else {
            neighbors = pathRepository.fetchPathWithRestriction(restriction1, restriction2);
            // System.out.println("Entrou no else");
        }
        
        // System.out.println("neighbors: " + neighbors);

        Map<Long, List<Long>> dicionario = new HashMap<>();

        for (int i = 0; i < neighbors.size(); i++) {
            if (dicionario.containsKey(neighbors.get(i).getSourceId())) {
                // A chave já existe, então adicionamos o valor à lista existente
                dicionario.get(neighbors.get(i).getSourceId()).add(neighbors.get(i).getTargetId());
            } else {
                // A chave não existe, então criamos uma nova lista e adicionamos o valor
                List<Long> novaLista = new ArrayList<>();
                novaLista.add(neighbors.get(i).getTargetId());
                dicionario.put(neighbors.get(i).getSourceId(), novaLista);
            }
        }

        Grafo grafo = new Grafo();
        grafo.criarGrafo(dicionario);

        return new ResponseEntity<>(grafo.encontrarCaminhoMinimo(begin, end), HttpStatus.OK);
    }
    @GetMapping("/AllPossibilities")
    public ResponseEntity<List<Map<String, Object>>> allPaths(@RequestParam(name = "begin") Long begin, @RequestParam(name = "end") Long end, @RequestParam(name = "maxDepth", defaultValue = "100") int maxDepth) {
        List<Path> neighbors = pathRepository.fetchPaths();
        Map<Long, List<Long>> adjacencyList = new HashMap<>();
    
        for (int i = 0; i < neighbors.size(); i++) {
            adjacencyList.computeIfAbsent(neighbors.get(i).getSourceId(), k -> new ArrayList<>()).add(neighbors.get(i).getTargetId());
        }
    
        List<Map<String, Object>> allPaths = new ArrayList<>();
        List<Long> currentPath = new ArrayList<>();
        Set<Long> visited = new HashSet<>();
    
        findAllPaths(adjacencyList, begin, end, currentPath, allPaths, visited, 0, maxDepth, neighbors);
    
        // Ordenar os caminhos com base no peso (em ordem crescente)
        allPaths.sort(Comparator.comparingInt(pathData -> (int) pathData.get("peso")));
    
        // Atribuir IDs únicos a cada caminho
        for (int i = 0; i < allPaths.size(); i++) {
            allPaths.get(i).put("id", i);
        }
    
        return new ResponseEntity<>(allPaths, HttpStatus.OK);
    }
    
    private void findAllPaths(Map<Long, List<Long>> graph, Long current, Long end, List<Long> currentPath, List<Map<String, Object>> allPaths, Set<Long> visited, int depth, int maxDepth, List<Path> neighbors) {
        currentPath.add(current);
        visited.add(current);
    
        if (current.equals(end)) {
            Map<String, Object> pathData = new HashMap<>();
            pathData.put("peso", calculateTotalWeight(currentPath, neighbors));
            pathData.put("caminho", new ArrayList<>(currentPath));
            allPaths.add(pathData);
        } else if (depth < maxDepth && graph.containsKey(current)) {
            for (Long neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    findAllPaths(graph, neighbor, end, currentPath, allPaths, visited, depth + 1, maxDepth, neighbors);
                }
            }
        }
    
        currentPath.remove(currentPath.size() - 1);
        visited.remove(current);
    }
    
    private int calculateTotalWeight(List<Long> pathList, List<Path> neighbors) {
        int totalWeight = 0;
        for (int i = 0; i < pathList.size() - 1; i++) {
            Long source = pathList.get(i);
            Long target = pathList.get(i + 1);
            for (Path path : neighbors) {
                if (path.getSourceId() == source && path.getTargetId() == target) {
                    totalWeight += path.getLength();
                    break;
                }
            }
        }
        return totalWeight;
    }
    

    // public ResponseEntity<Map<Long, List<Long>>> pathIndex2() {
    // List<Path> neighbors = pathRepository.fetchPaths();

    /**
     * Retorna um caminho específico pelo seu ID.
     *
     * @param id O ID do caminho a ser buscado.
     * @return ResponseEntity contendo o objeto Path e HttpStatus.OK
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Path> pathById(@PathVariable Long id) {
        Path path = pathRepository.fetchPathById(id);
        return new ResponseEntity<>(path, HttpStatus.OK);
    }

    /**
     * Retorna todos os caminhos que têm o nó de origem com o ID fornecido.
     *
     * @param id O ID do nó de origem.
     * @return ResponseEntity contendo uma lista de objetos Path e HttpStatus.OK
     */
    @GetMapping("/sourceId={id}")
    public ResponseEntity<List<Path>> pathBySourceId(@PathVariable Long id) {
        List<Path> neighbors = pathRepository.fetchSourceNeighbors(id);
        return new ResponseEntity<>(neighbors, HttpStatus.OK);
    }

    /**
     * Retorna todos os caminhos que têm o nó de destino com o ID fornecido.
     *
     * @param id O ID do nó de destino.
     * @return ResponseEntity contendo uma lista de objetos Path e HttpStatus.OK
     */
    @GetMapping("/targetId={id}")
    public ResponseEntity<List<Path>> pathByTargetId(@PathVariable Long id) {
        List<Path> neighbors = pathRepository.fetchTargetNeighbors(id);
        return new ResponseEntity<>(neighbors, HttpStatus.OK);
    }

    /**
     * Cria um novo caminho com base nos dados fornecidos.
     *
     * @param request Os dados do caminho a serem criados.
     * @return ResponseEntity contendo o objeto Path criado e HttpStatus.CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<Path> create(@RequestBody PathDTO request) {
        try {
            long requestSourceId = request.getSourceId();
            long requestTargetId = request.getTargetId();
            long distance = request.getLength();
            String type = request.getType();
            if (requestSourceId == -1 || requestTargetId == -1) {
                throw new RuntimeException();
            }
            Path path = pathRepository.createPath(requestSourceId, requestTargetId, distance, type);
            return new ResponseEntity<>(path, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Remove um caminho com base nos dados fornecidos.
     *
     * @param request Os dados do caminho a serem removidos.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro e HttpStatus correspondente.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody PathDTO request) {
        String msg;
        try {
            long requestSourceId = request.getSourceId();
            long requestTargetId = request.getTargetId();
            if (request.getTargetId() == -1 || request.getSourceId() == -1) {
                throw new RuntimeException();
            }
            pathRepository.removePath(requestSourceId, requestTargetId);
            msg = "Caminho removido com sucesso";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg = "Algo deu errado, tente novamente mais tarde.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
