package com.inteli.onebeertwo.services;

import com.inteli.onebeertwo.models.Valve;
import com.inteli.onebeertwo.repositories.ValveRepository;
import com.inteli.onebeertwo.requests.ValveRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de serviço para operações relacionadas a Válvulas.
 */
@Service
public class ValveService {

    // Injeção do repositório de Válvulas
    @Autowired
    private ValveRepository valveRepository;

    /**
     * Método para criar uma nova Válvula com base na requisição.
     *
     * @param request A requisição para criar a Válvula.
     * @return A Válvula criada.
     */
    public Valve createValve(ValveRequest request) {
        Valve valve = new Valve();
        valve.setName(request.getName());
        valve.setType(request.getType());
        valveRepository.save(valve);
        return valve;
    }

    /**
     * Método para obter todas as Válvulas.
     *
     * @return Uma lista de todas as Válvulas.
     */
    public List<Valve> getAllValves() {
        return valveRepository.findAll();
    }

    /**
     * Método para obter uma Válvula pelo nome.
     *
     * @param name O nome da Válvula a ser procurada.
     * @return A Válvula correspondente.
     * @throws ResponseStatusException com status HTTP 404 se a Válvula não for encontrada.
     */
    public Valve getValveByName(String name){
        return valveRepository.findValveByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para obter uma Válvula pelo ID.
     *
     * @param id O ID da Válvula a ser procurada.
     * @return A Válvula correspondente.
     * @throws ResponseStatusException com status HTTP 404 se a Válvula não for encontrada.
     */
    public Valve getValveById(Long id){
        return valveRepository.findValveById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para deletar uma Válvula pelo nome.
     *
     * @param name O nome da Válvula a ser deletada.
     */
    public void deleteValveByName(String name){
        Valve valve = getValveByName(name);
        valveRepository.delete(valve);
    }

    /**
     * Método para deletar uma Válvula pelo ID.
     *
     * @param id O ID da Válvula a ser deletada.
     */
    public void deleteValveById(Long id){
        Valve valve = getValveById(id);
        valveRepository.delete(valve);
    }

    /**
     * Método para atualizar uma Válvula com base no ID e na requisição.
     *
     * @param id      O ID da Válvula a ser atualizada.
     * @param request A requisição contendo os dados de atualização.
     * @return A Válvula atualizada.
     */
    public Valve updateValve(Long id, ValveRequest request) {
        Valve existingValve = valveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Valve not found with ID: " + id));

        BeanUtils.copyProperties(request, existingValve);

        return valveRepository.save(existingValve);
    }    

    /**
     * Método para obter o tipo de uma Válvula pelo ID.
     *
     * @param id O ID da Válvula.
     * @return O tipo da Válvula.
     * @throws ResponseStatusException com status HTTP 404 se a Válvula não for encontrada.
     */
    public String getValveType(Long id) {
        Valve valve = valveRepository.findValveById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Valve not found with ID: " + id));
        return valve.getType();
    }

    /**
     * Método para obter o tipo de uma Válvula pelo nome.
     *
     * @param name O nome da Válvula.
     * @return O tipo da Válvula.
     * @throws ResponseStatusException com status HTTP 404 se a Válvula não for encontrada.
     */
    public String getValveTypeByName(String name) {
        Valve valve = valveRepository.findValveByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Valve not found with ID: " + name));
        return valve.getType();
    }
    
    /**
     * Método para verificar se existe uma relação de uma Válvula para um Tanque.
     *
     * @param valveName O nome da Válvula.
     * @param tankName  O nome do Tanque.
     * @return `true` se a relação existe, `false` caso contrário.
     */
    public Boolean checkRelationshipValveTank(String valveName, String tankName){
        return valveRepository.checkRelationshipValveTank(valveName, tankName);
    }

    /**
     * Método para criar uma relação de uma Válvula para um Tanque.
     *
     * @param valveName O nome da Válvula.
     * @param tankName  O nome do Tanque.
     */
    public void createRelationshipValveTank(String valveName, String tankName){
        valveRepository.createRelationshipValveTank(valveName, tankName);
    }

    /**
     * Mapa para armazenar mapeamento de IDs de Válvulas para nomes de Válvulas.
     */
    private final Map<Long, String> valveIdToNameMap = new HashMap<>();

    /**
     * Verifica se uma entidade com o ID fornecido é uma Válvula.
     *
     * @param id O ID a ser verificado.
     * @return `true` se for uma Válvula, `false` caso contrário.
     */
    public boolean isValve(Long id) {
        boolean isValve = valveRepository.findValveById(Long.valueOf(id)).isPresent();
        if (isValve) {
            valveIdToNameMap.put(Long.valueOf(id), valveRepository.findValveById(Long.valueOf(id)).get().getName());
        }
        return isValve;
    }

    /**
     * Verifica se uma entidade com o nome fornecido é uma Válvula.
     *
     * @param name O nome a ser verificado.
     * @return `true` se for uma Válvula, `false` caso contrário.
     */
    public boolean isValveByName(String name) {
        boolean isValve = valveRepository.findValveByName(name).isPresent();
        return isValve;
    }

    /**
     * Obtém o mapeamento de IDs de Válvulas para nomes de Válvulas.
     *
     * @return O mapa de mapeamento.
     */
    public Map<Long, String> getValveIdToNameMap() {
        return valveIdToNameMap;
    }

    /**
     * Popula o mapa de mapeamento de IDs de Válvulas para nomes de Válvulas.
     */
    public void populateValveIdToNameMap() {
        List<Valve> allValves = valveRepository.findAll();
        for (Valve valve : allValves) {
            valveIdToNameMap.put(valve.getId(), valve.getName());
        }
    }
}
