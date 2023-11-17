package com.inteli.onebeertwo.models;
import java.util.*;

/**
 * Esta classe representa um grafo direcionado usado para cálculos de caminho mínimo
 * com os algoritmos Dijkstra e A*.
 */
public class Grafo {
    // Estrutura de dados principal para armazenar o grafo
    private HashMap<Long, List<Pair>> grafo;

    // Variáveis para geração de números aleatórios
    long seed = 73;
    Random random = new Random(seed);

    /**
     * Classe interna para armazenar pares de nó destino e peso da aresta.
     */
    static class Pair {
        long noDestino;
        long peso;

        /**
         * Construtor para criar um par com nó destino e peso.
         *
         * @param noDestino O nó de destino da aresta.
         * @param peso      O peso da aresta.
         */
        Pair(long noDestino, long peso) {
            this.noDestino = noDestino;
            this.peso = peso;
        }
    }

    /**
     * Construtor do grafo.
     */

    public Grafo() {
        grafo = new HashMap<>();
    }

    /**
     * Método para adicionar uma aresta com peso aleatório.
     *
     * @param noInicial O nó de origem da aresta.
     * @param noDestino O nó de destino da aresta.
     */

    public void adicionarAresta(long noInicial, long noDestino) {
        long pesoAleatorio = random.nextLong(10) + 1;
        adicionarAresta(noInicial, noDestino, pesoAleatorio);
    }

    /**
     * Método para adicionar uma aresta com peso específico.
     *
     * @param noInicial O nó de origem da aresta.
     * @param noDestino O nó de destino da aresta.
     * @param peso      O peso da aresta.
     */
    public void adicionarAresta(long noInicial, long noDestino, long peso) {
        if (!grafo.containsKey(noInicial)) {
            grafo.put(noInicial, new ArrayList<>());
        }
        grafo.get(noInicial).add(new Pair(noDestino, peso));
    }

    /**
     * Método específico para criar um grafo com pesos 1 para uso com A*.
     *
     * @param listaAdjacencia O mapa de lista de adjacência com conexões entre nós.
     */

    public void criarGrafoParaAStar(Map<Long, List<Long>> listaAdjacencia) {
        for (Map.Entry<Long, List<Long>> entry : listaAdjacencia.entrySet()) {
            long noInicial = entry.getKey();
            List<Long> conexoes = entry.getValue();
            for (long noDestino : conexoes) {
                adicionarAresta(noInicial, noDestino, 1L);
            }
        }
    }

    /**
     * Método geral para criar um grafo.
     *
     * @param listaAdjacencia O mapa de lista de adjacência com conexões entre nós.
     */
    public void criarGrafo(Map<Long, List<Long>> listaAdjacencia) {
        for (Map.Entry<Long, List<Long>> entry : listaAdjacencia.entrySet()) {
            long noInicial = entry.getKey();
            List<Long> conexoes = entry.getValue();

            for (long noDestino : conexoes) {
                adicionarAresta(noInicial, noDestino);
            }
        }
    }

    /**
     * Método para imprimir o grafo na saída padrão.
     */
    public void imprimirGrafo() {
        for (Map.Entry<Long, List<Pair>> entry : grafo.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            List<Pair> conexoes = entry.getValue();
            for (Pair par : conexoes) {
                System.out.print(par.noDestino + "(" + par.peso + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Método para encontrar o caminho mínimo usando o algoritmo de Dijkstra.
     *
     * @param noInicial O nó de origem.
     * @param noDestino O nó de destino.
     * @return Uma lista de nós representando o caminho mínimo encontrado.
     *         Retorna null se não há caminho.
     */

    public List<Long> encontrarCaminhoMinimo(long noInicial, long noDestino) {
        // Dicionário para manter o custo mínimo de alcançar cada nó a partir do nó inicial
        Map<Long, Long> distancia = new HashMap<>();
        
        // Inicialize todas as distâncias com infinito, exceto o nó inicial com distância 0
        for (Long no : grafo.keySet()) {
            distancia.put(no, Long.MAX_VALUE);
        }
        
        // Dicionário para rastrear o nó anterior no caminho mínimo
        Map<Long, Long> anterior = new HashMap<>();
        
        // Lista para manter os nós a serem visitados
        LinkedList<Long> filaVisita = new LinkedList<>();
        
        // Inicialize a distância do nó inicial como 0
        distancia.put(noInicial, 0L);
        
        // Adicione o nó inicial à fila de visitação
        filaVisita.add(noInicial);
        
        while (!filaVisita.isEmpty()) {
            Long noAtual = filaVisita.poll();
            try {
                // Se chegamos ao nó de destino, construa o caminho mínimo e retorne
                if (noAtual == noDestino) {
                    List<Long> caminhoMinimo = new ArrayList<>();
                    while (noAtual != noInicial) {
                        caminhoMinimo.add(noAtual);
                        noAtual = anterior.get(noAtual);
                    }
                    caminhoMinimo.add(noInicial);
                    Collections.reverse(caminhoMinimo);
                    return caminhoMinimo;
                }
                
                // Explore os vizinhos do nó atual
                List<Pair> vizinhos = grafo.get(noAtual);
                if (vizinhos != null) {
                    for (Pair vizinho : vizinhos) {
                        long noVizinho = vizinho.noDestino;
                        long pesoAresta = vizinho.peso;
                        long novaDistancia = distancia.get(noAtual) + pesoAresta;
                        
                        if (novaDistancia < distancia.get(noVizinho)) {
                            distancia.put(noVizinho, novaDistancia);
                            anterior.put(noVizinho, noAtual);
                            
                            // Adicione o nó vizinho à fila de visitação
                            filaVisita.add(noVizinho);
                        }
                    }
                }
            } 
            catch (Exception e) {
                System.out.println(e);
                System.out.println("Error in the node " + noAtual + "");
                continue;
            }
        }
        
        // Não há caminho para o nó destino a partir do nó inicial
        return null;
    }

    /**
     * Método heurístico usado pelo A*.
     *
     * @param atual   O nó atual.
     * @param destino O nó de destino.
     * @return O valor heurístico.
     */

    private long heuristica(long atual, long destino) {
        // adiciona apenas uma constante ao custo, pois consideramos que o custo de cada aresta é 1 e queremos minimizar com base na quantidade de nodos.
        return 1;
    }

    /**
     * Método para encontrar o caminho mínimo usando o algoritmo A*.
     *
     * @param noInicial O nó de origem.
     * @param noDestino O nó de destino.
     * @return Uma lista de nós representando o caminho mínimo encontrado.
     *         Retorna null se não há caminho.
     */
    public List<Long> encontrarCaminhoMinimoAStar(long noInicial, long noDestino) {
        // Estruturas para armazenar distâncias, custos, nós anteriores e fila de prioridade
        Map<Long, Long> distancia = new HashMap<>();
        Map<Long, Long> custo = new HashMap<>();
        Map<Long, Long> anterior = new HashMap<>();
        PriorityQueue<Long> filaPrioridade = new PriorityQueue<>(Comparator.comparing(custo::get));

        // Inicializações
        for (Long no : grafo.keySet()) {
            distancia.put(no, Long.MAX_VALUE);
            custo.put(no, Long.MAX_VALUE);
        }

        distancia.put(noInicial, 0L);
        custo.put(noInicial, heuristica(noInicial, noDestino));
        filaPrioridade.add(noInicial);

        // Lógica principal do algoritmo A*
        while (!filaPrioridade.isEmpty()) {
            Long noAtual = filaPrioridade.poll();
            try {

                if (noAtual == noDestino) {
                    List<Long> caminhoMinimo = new ArrayList<>();
                    while (noAtual != null) {
                        caminhoMinimo.add(noAtual);
                        noAtual = anterior.get(noAtual);
                    }
                    Collections.reverse(caminhoMinimo);
                    return caminhoMinimo;
                }

                List<Pair> vizinhos = grafo.get(noAtual);
                if (vizinhos != null) {
                    for (Pair vizinho : vizinhos) {
                        long noVizinho = vizinho.noDestino;
                        long pesoAresta = vizinho.peso;
                        long novaDistancia = distancia.get(noAtual) + pesoAresta;

                        if (novaDistancia < distancia.get(noVizinho)) {
                            distancia.put(noVizinho, novaDistancia);
                            long estimativaTotal = novaDistancia + heuristica(noVizinho, noDestino);
                            custo.put(noVizinho, estimativaTotal);
                            anterior.put(noVizinho, noAtual);
                            filaPrioridade.add(noVizinho);
                        }
                    }
                }

            } 
            catch (Exception e) {
                System.out.println(e);
                System.out.println("Error in the node " + noAtual + "");
                continue;
            }

        }

        return null; // Não há caminho
    }
}

