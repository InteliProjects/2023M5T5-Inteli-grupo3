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
import com.inteli.onebeertwo.models.Tank;
import com.inteli.onebeertwo.objects.TankDTO;
import com.inteli.onebeertwo.requests.TankRequest;  
import com.inteli.onebeertwo.services.TankService;

/**
 * Esta classe define os pontos de extremidade da API REST para gerenciar objetos Tank (tanques).
 */
@RestController
@RequestMapping("/api/tanks")
@CrossOrigin
public class TankController {
    private final TankService tankService;

    public TankController(TankService tankService){
        this.tankService = tankService;
    }

    /**
     * Método para listar todos os tanques.
     *
     * @return ResponseEntity contendo uma lista de objetos Tank e HttpStatus.OK
     */
    @GetMapping("/")
    public ResponseEntity<List<Tank>> tankIndex(){
        return new ResponseEntity<>(tankService.getAllTanks(), HttpStatus.OK);
    }

    /**
     * Método para registrar um novo tanque.
     *
     * @param request Os dados do tanque a serem registrados.
     * @return ResponseEntity contendo o objeto Tank registrado e HttpStatus.CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<TankDTO> signUp(@RequestBody TankRequest request){
        Tank tank = tankService.createTank(request);
    
        TankDTO responseTank = new TankDTO(tank.getName(), tank.getVolume());
    
        return new ResponseEntity<TankDTO>(responseTank, HttpStatus.CREATED);
    }
    
    /**
     * Método para atualizar as propriedades de um tanque através do ID.
     *
     * @param id      O ID do tanque a ser atualizado.
     * @param request Os novos dados do tanque.
     * @return ResponseEntity contendo o objeto Tank atualizado e HttpStatus.OK
     */
    @PutMapping("/id={id}")
    public ResponseEntity<TankDTO> updateTank(@PathVariable Long id, @RequestBody TankRequest request) {
        Tank tank = tankService.updateTank(id, request);
    
        TankDTO responseTank = new TankDTO(tank.getName(), tank.getVolume());
    
        return new ResponseEntity<>(responseTank, HttpStatus.OK);
    }

    /**
     * Método para obter detalhes de um tanque pelo nome.
     *
     * @param name O nome do tanque a ser buscado.
     * @return ResponseEntity contendo o objeto Tank encontrado e HttpStatus.OK
     */
    @GetMapping("/name={name}")
    public ResponseEntity<Tank> tankDetailsByName(@PathVariable String name){
        Tank tank = tankService.getTankByName(name);
        return new ResponseEntity<Tank>(tank, HttpStatus.OK);
    }

    /**
     * Método para obter detalhes de um tanque pelo ID.
     *
     * @param id O ID do tanque a ser buscado.
     * @return ResponseEntity contendo o objeto Tank encontrado e HttpStatus.OK
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Tank> tankDetailsById(@PathVariable Long id){
        Tank tank = tankService.getTankById(id);
        return new ResponseEntity<Tank>(tank, HttpStatus.OK);
    }

    /**
     * Método para deletar um tanque pelo nome.
     *
     * @param name O nome do tanque a ser deletado.
     * @return ResponseEntity contendo uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/name={name}")
    public ResponseEntity<String> deleteTankByName(@PathVariable String name) {
        tankService.deleteTankByName(name);
        return new ResponseEntity<>("Tank deleted successfully by name", HttpStatus.OK);
    }

    /**
     * Método para deletar um tanque pelo ID.
     *
     * @param id O ID do tanque a ser deletado.
     * @return ResponseEntity contendo uma mensagem de sucesso e HttpStatus.OK
     */
    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteTankById(@PathVariable Long id) {
        tankService.deleteTankById(id);
        return new ResponseEntity<>("Tank deleted successfully by ID", HttpStatus.OK);
    }
}
