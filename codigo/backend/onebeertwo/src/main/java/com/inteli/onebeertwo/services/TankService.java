package com.inteli.onebeertwo.services;

import com.inteli.onebeertwo.models.Tank;
import com.inteli.onebeertwo.repositories.TankRepository;
import com.inteli.onebeertwo.requests.TankRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de serviço para operações relacionadas a Tanques.
 */
@Service
public class TankService {

    // Injeção do repositório de Tanques
    @Autowired
    private final TankRepository tankRepository;

    /**
     * Construtor que recebe o repositório de Tanques como parâmetro.
     *
     * @param tankRepository O repositório de Tanques a ser injetado.
     */
    public TankService(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    /**
     * Método para criar um novo Tanque com base na requisição.
     *
     * @param request A requisição para criar o Tanque.
     * @return O Tanque criado.
     */
    public Tank createTank(TankRequest request) {
        Tank tank = new Tank();
        tank.setName(request.getName()); // Define o nome do tanque
        tank.setVolume(request.getVolume()); // Define o volume do tanque
        tankRepository.save(tank);
        return tank;
    }

    /**
     * Método para atualizar um Tanque com base no ID e na requisição.
     *
     * @param id      O ID do Tanque a ser atualizado.
     * @param request A requisição contendo os dados de atualização.
     * @return O Tanque atualizado.
     */
    public Tank updateTank(Long id, TankRequest request) {
        Tank existingTank = tankRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with ID: " + id));

        // Atualize as propriedades do request para o tanque existente
        BeanUtils.copyProperties(request, existingTank);

        return tankRepository.save(existingTank);
    }

    /**
     * Método para obter todos os Tanques.
     *
     * @return Uma lista de todos os Tanques.
     */
    public List<Tank> getAllTanks() {
        return tankRepository.findAll();
    }

    /**
     * Método para obter um Tanque pelo nome.
     *
     * @param name O nome do Tanque a ser procurado.
     * @return O Tanque correspondente.
     * @throws ResponseStatusException com status HTTP 404 se o Tanque não for encontrado.
     */
    public Tank getTankByName(String name){
        return tankRepository.findTankByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para obter um Tanque pelo ID.
     *
     * @param id O ID do Tanque a ser procurado.
     * @return O Tanque correspondente.
     * @throws ResponseStatusException com status HTTP 404 se o Tanque não for encontrado.
     */
    public Tank getTankById(Long id){
        return tankRepository.findTankById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para deletar um Tanque pelo nome.
     *
     * @param name O nome do Tanque a ser deletado.
     */
    public void deleteTankByName(String name){
        Tank tank = getTankByName(name);
        tankRepository.delete(tank);
    }

    /**
     * Método para deletar um Tanque pelo ID.
     *
     * @param id O ID do Tanque a ser deletado.
     */
    public void deleteTankById(Long id){
        Tank tank = getTankById(id);
        tankRepository.delete(tank);
    }

    /**
     * Mapa para armazenar mapeamento de IDs de Tanques para nomes de Tanques.
     */
    private final Map<Long, String> tankIdToNameMap = new HashMap<>();

    /**
     * Verifica se uma entidade com o ID fornecido é um Tanque.
     *
     * @param id O ID a ser verificado.
     * @return `true` se for um Tanque, `false` caso contrário.
     */
    public boolean isTank(Long id) {
        boolean isTank = tankRepository.findTankById(Long.valueOf(id)).isPresent();
        if (isTank) {
            tankIdToNameMap.put(Long.valueOf(id), tankRepository.findTankById(Long.valueOf(id)).get().getName());
        }
        return isTank;
    }

    /**
     * Obtém o mapeamento de IDs de Tanques para nomes de Tanques.
     *
     * @return O mapa de mapeamento.
     */
    public Map<Long, String> getTankIdToNameMap() {
        return tankIdToNameMap;
    }

    /**
     * Popula o mapa de mapeamento de IDs de Tanques para nomes de Tanques.
     */
    public void populateTankIdToNameMap() {
        List<Tank> allTanks = tankRepository.findAll();
        for (Tank tank : allTanks) {
            tankIdToNameMap.put(tank.getId(), tank.getName());
        }
    }
}
