package com.inteli.onebeertwo.services;

import com.inteli.onebeertwo.models.INOUT;
import com.inteli.onebeertwo.repositories.INOUTRepository;
import com.inteli.onebeertwo.requests.INOUTRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de serviço para operações relacionadas a Válvula INOUT.
 */
@Service
public class INOUTService {

    /**
     * Injeção do repositório de INOUT
     */
    @Autowired
    private INOUTRepository inoutRepository;

    /**
     * Método para criar uma nova válvula INOUT com base na requisição.
     *
     * @param request A requisição para criar a válvula INOUT.
     * @return A válvula INOUT criada.
     */
    public INOUT createINOUT(INOUTRequest request) {
        INOUT inout = new INOUT();
        inout.setName(request.getName());
        inoutRepository.save(inout);
        return inout;
    }

    /**
     * Método para obter todas as INOUTs.
     *
     * @return Uma lista de todas as válvulas INOUT.
     */
    public List<INOUT> getAllINOUTs() {
        return inoutRepository.findAll();
    }

    /**
     * Método para obter uma INOUT pelo nome.
     *
     * @param name O nome da INOUT a ser procurada.
     * @return A INOUT correspondente.
     * @throws ResponseStatusException com status HTTP 404 se a INOUT não for encontrada.
     */
    public INOUT getINOUTByName(String name){
        return inoutRepository.findINOUTByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para obter uma INOUT pelo ID.
     *
     * @param id O ID da INOUT a ser procurada.
     * @return A INOUT correspondente.
     * @throws ResponseStatusException com status HTTP 404 se a INOUT não for encontrada.
     */
    public INOUT getINOUTById(Long id){
        return inoutRepository.findINOUTById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para deletar uma INOUT pelo nome.
     *
     * @param name O nome da INOUT a ser deletada.
     */
    public void deleteINOUTByName(String name){
        INOUT inout = getINOUTByName(name);
        inoutRepository.delete(inout);
    }

    /**
     * Método para deletar uma INOUT pelo ID.
     *
     * @param id O ID da INOUT a ser deletada.
     */
    public void deleteINOUTById(Long id){
        INOUT inout = getINOUTById(id);
        inoutRepository.delete(inout);
    }

    /**
     * Método para atualizar uma INOUT com base no ID e na requisição.
     *
     * @param id      O ID da INOUT a ser atualizada.
     * @param request A requisição contendo os dados de atualização.
     * @return A INOUT atualizada.
     */
    public INOUT updateINOUT(Long id, INOUTRequest request) {
        INOUT existingINOUT = inoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "INOUT not found with ID: " + id));

        BeanUtils.copyProperties(request, existingINOUT);

        return inoutRepository.save(existingINOUT);
    }

    /**
     * Mapa para armazenar mapeamento de IDs de INOUT para nomes de INOUT.
     */
    private final Map<Long, String> inoutIdToNameMap = new HashMap<>();

    /**
     * Verifica se uma entidade com o ID fornecido é uma INOUT.
     *
     * @param id O ID a ser verificado.
     * @return `true` se for uma INOUT, `false` caso contrário.
     */
    public boolean isInout(Long id) {
        boolean isInout = inoutRepository.findINOUTById(Long.valueOf(id)).isPresent();
        if (isInout) {
            inoutIdToNameMap.put(Long.valueOf(id), inoutRepository.findINOUTById(Long.valueOf(id)).get().getName());
        }
        return isInout;
    }

    /**
     * Obtém o mapeamento de IDs de INOUTs para nomes de INOUTs.
     *
     * @return O mapa de mapeamento.
     */
    public Map<Long, String> getInoutIdToNameMap() {
        return inoutIdToNameMap;
    }

    /**
     * Popula o mapa de mapeamento de IDs de INOUTs para nomes de INOUTs.
     */
    public void populateInoutIdToNameMap() {
        List<INOUT> allInouts = inoutRepository.findAll();
        for (INOUT inout : allInouts) {
            inoutIdToNameMap.put(inout.getId(), inout.getName());
        }
    }
}
