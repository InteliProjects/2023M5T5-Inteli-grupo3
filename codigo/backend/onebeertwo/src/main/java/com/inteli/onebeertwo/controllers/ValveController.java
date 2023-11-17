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

// Importando arquivos
import com.inteli.onebeertwo.models.Valve;
import com.inteli.onebeertwo.objects.ValveDTO;
import com.inteli.onebeertwo.requests.ValveRequest;
import com.inteli.onebeertwo.requests.ValveToTankRequest;
import com.inteli.onebeertwo.services.ValveService;

/**
 * Esta classe define os pontos de extremidade da API REST para gerenciar objetos Valve (válvulas).
 */
@RestController
@RequestMapping("/api/valves")
@CrossOrigin
public class ValveController {
    private final ValveService valveService;

    public ValveController(ValveService valveService){
        this.valveService = valveService;
    }

    /**
     * Método para listar todas as válvulas.
     *
     * @return ResponseEntity contendo uma lista de objetos Valve e HttpStatus.OK
     */
    @GetMapping("/")
    public ResponseEntity<List<Valve>> valveIndex(){
        return new ResponseEntity<>(valveService.getAllValves(), HttpStatus.OK);
    }

    /**
     * Método para registrar uma nova válvula.
     *
     * @param request Os dados da válvula a serem registrados.
     * @return ResponseEntity contendo o objeto Valve registrado e HttpStatus.CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<ValveDTO> signUp(@RequestBody ValveRequest request){
        Valve valve = valveService.createValve(request);

        ValveDTO responseValve = new ValveDTO(valve.getName(), valve.getType());

        return new ResponseEntity<ValveDTO>(responseValve, HttpStatus.CREATED);
    }

    /**
     * Método para obter detalhes de uma válvula pelo nome.
     *
     * @param name O nome da válvula a ser buscado.
     * @return ResponseEntity contendo o objeto Valve encontrado e HttpStatus.OK
     */
    @GetMapping("/name={name}")
    public ResponseEntity<Valve> valveDetailsByName(@PathVariable String name){
        Valve valve = valveService.getValveByName(name);
        return new ResponseEntity<Valve>(valve, HttpStatus.OK);
    }

    /**
     * Método para obter detalhes de uma válvula pelo ID.
     *
     * @param id O ID da válvula a ser buscado.
     * @return ResponseEntity contendo o objeto Valve encontrado e HttpStatus.OK
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Valve> valveDetailsById(@PathVariable Long id){
        Valve valve = valveService.getValveById(id);
        return new ResponseEntity<Valve>(valve, HttpStatus.OK);
    }

    /**
     * Método para deletar uma válvula pelo nome.
     *
     * @param name O nome da válvula a ser deletado.
     * @return ResponseEntity contendo uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/name={name}")
    public ResponseEntity<String> deleteValveByName(@PathVariable String name) {
        valveService.deleteValveByName(name);
        return new ResponseEntity<>("Valve deleted successfully by name", HttpStatus.OK);
    }

    /**
     * Método para deletar uma válvula pelo ID.
     *
     * @param id O ID da válvula a ser deletado.
     * @return ResponseEntity contendo uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteValveById(@PathVariable Long id) {
        valveService.deleteValveById(id);
        return new ResponseEntity<>("Valve deleted successfully by ID", HttpStatus.OK);
    }

    /**
     * Método para atualizar as propriedades de uma válvula através do ID.
     *
     * @param id      O ID da válvula a ser atualizada.
     * @param request Os novos dados da válvula.
     * @return ResponseEntity contendo o objeto Valve atualizado e HttpStatus.OK
     */
    @PutMapping("/id={id}")
    public ResponseEntity<ValveDTO> updateValve(@PathVariable Long id, @RequestBody ValveRequest request) {
        Valve valve = valveService.updateValve(id, request);

        ValveDTO responseValve = new ValveDTO(valve.getName(), valve.getType());

        return new ResponseEntity<>(responseValve, HttpStatus.OK);
    }

    /**
     * Método para criar uma conexão de válvula para tanque.
     *
     * @param request Os dados da conexão a serem criados.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro e HttpStatus correspondente.
     */
    @PostMapping("/relationshipValveTank")
    public ResponseEntity<String> connectValveToTank(@RequestBody ValveToTankRequest request){
        String valveName = request.getValveName();
        String tankName = request.getTankName();

        if(valveService.checkRelationshipValveTank(valveName, tankName)){
            return new ResponseEntity<String>("Conexão já existente entre Válvula " + valveName + " e Tanque " + tankName, HttpStatus.OK);
        }

        valveService.createRelationshipValveTank(valveName, tankName);

        return new ResponseEntity<String>("Conexão entre Válvula " + valveName + " e Tanque " + tankName, HttpStatus.OK);
    }
}
