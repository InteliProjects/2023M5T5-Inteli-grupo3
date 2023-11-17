package com.inteli.onebeertwo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inteli.onebeertwo.models.INOUT;
import com.inteli.onebeertwo.objects.INOUTDTO;
import com.inteli.onebeertwo.requests.INOUTRequest;
import com.inteli.onebeertwo.services.INOUTService;

/**
 * Esta classe define os pontos de extremidade da API REST para gerenciar objetos INOUT.
 */
@RestController
@RequestMapping("/api/inouts")
@CrossOrigin
public class INOUTController {
    private final INOUTService inoutService;

    public INOUTController(INOUTService inoutService) {
        this.inoutService = inoutService;
    }

    /**
     * Obtém uma lista de todos os objetos INOUT.
     *
     * @return ResponseEntity contendo uma lista de objetos INOUT e HttpStatus.OK
     */
    @GetMapping("/")
    public ResponseEntity<List<INOUT>> inoutIndex() {
        return new ResponseEntity<>(inoutService.getAllINOUTs(), HttpStatus.OK);
    }

    /**
     * Registra um novo objeto INOUT.
     *
     * @param request O corpo da solicitação que contém os dados do INOUT a ser registrado.
     * @return ResponseEntity contendo um objeto INOUTDTO e HttpStatus.CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<INOUTDTO> signUp(@RequestBody INOUTRequest request) {
        INOUT inout = inoutService.createINOUT(request);

        INOUTDTO responseINOUT = new INOUTDTO(inout.getName());

        return new ResponseEntity<INOUTDTO>(responseINOUT, HttpStatus.CREATED);
    }

    /**
     * Obtém os detalhes de um objeto INOUT pelo nome.
     *
     * @param name O nome do objeto INOUT a ser buscado.
     * @return ResponseEntity contendo o objeto INOUT e HttpStatus.OK
     */
    @GetMapping("/name={name}")
    public ResponseEntity<INOUT> inoutDetailsByName(@PathVariable String name) {
        INOUT inout = inoutService.getINOUTByName(name);
        return new ResponseEntity<INOUT>(inout, HttpStatus.OK);
    }

    /**
     * Obtém os detalhes de um objeto INOUT pelo ID.
     *
     * @param id O ID do objeto INOUT a ser buscado.
     * @return ResponseEntity contendo o objeto INOUT e HttpStatus.OK
     */
    @GetMapping("/id={id}")
    public ResponseEntity<INOUT> inoutDetailsById(@PathVariable Long id) {
        INOUT inout = inoutService.getINOUTById(id);
        return new ResponseEntity<INOUT>(inout, HttpStatus.OK);
    }

    /**
     * Exclui um objeto INOUT pelo nome.
     *
     * @param name O nome do objeto INOUT a ser excluído.
     * @return ResponseEntity com uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/name={name}")
    public ResponseEntity<String> deleteINOUTByName(@PathVariable String name) {
        inoutService.deleteINOUTByName(name);
        return new ResponseEntity<>("INOUT excluído com sucesso pelo nome", HttpStatus.OK);
    }

    /**
     * Exclui um objeto INOUT pelo ID.
     *
     * @param id O ID do objeto INOUT a ser excluído.
     * @return ResponseEntity com uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteINOUTById(@PathVariable Long id) {
        inoutService.deleteINOUTById(id);
        return new ResponseEntity<>("INOUT excluído com sucesso pelo ID", HttpStatus.OK);
    }

    /**
     * Atualiza as propriedades de um objeto INOUT pelo ID.
     *
     * @param id      O ID do objeto INOUT a ser atualizado.
     * @param request O corpo da solicitação que contém os dados atualizados do INOUT.
     * @return ResponseEntity contendo um objeto INOUTDTO atualizado e HttpStatus.OK
     */
    @PutMapping("/id={id}")
    public ResponseEntity<INOUTDTO> updateINOUT(@PathVariable Long id, @RequestBody INOUTRequest request) {
        INOUT inout = inoutService.updateINOUT(id, request);

        INOUTDTO responseINOUT = new INOUTDTO(inout.getName());

        return new ResponseEntity<>(responseINOUT, HttpStatus.OK);
    }
}
