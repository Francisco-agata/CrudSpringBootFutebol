package com.example.timao.controller;

import com.example.timao.dto.PartidaDTO;
import com.example.timao.error.RecordNotFoundException;
import com.example.timao.service.PartidaService;
import com.example.timao.model.Partida;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class PartidaController {
    @Autowired
    PartidaService service;

    // Ver todas as partidas cadastradas
    // http://localhost:8080/partida
    @GetMapping("/partida")
    public List<Partida> getAllPartida() {
        return service.findAll();
    }

    // Adicionar partida
    // http://localhost:8080/partida
    @PostMapping("/partida")
    public ResponseEntity<Partida> addUser(@Valid @RequestBody Partida partida) {
        System.out.println("AddUser");
        Partida newPartida = service.save(partida);
        return new ResponseEntity<Partida>(newPartida, HttpStatus.CREATED);
    }

    // Puxar uma partida
    // http://localhost:8080/partida/1
    @GetMapping(path = "/partida/{id}")
    public ResponseEntity<Partida> getPartida(@PathVariable("id") Integer id) {
        Optional<Partida> partida = service.findById(id);
        if (partida.isEmpty()) {
            throw new RecordNotFoundException("Partida " + id + "não existe");
        }

        return new ResponseEntity<Partida>(partida.get(), HttpStatus.FOUND);
    }

    //Puxar um estadio
    // http://localhost:8081/buscarPorEstadio
   @GetMapping("/partida/buscarPorEstadio")
    public ResponseEntity<?> getEstadio(@RequestBody PartidaDTO partidaDTO){
        String estadio = partidaDTO.getEstadio();
        List<Partida> partida = service.findByEstadio(estadio);
        if (estadio.isEmpty()) {
            throw new RecordNotFoundException("Partida " + estadio + "não existe");
        }
        return ResponseEntity.ok(partida);
    }

    // atualiza as partida
    // http://localhost:8080/partida/{id}
    @PutMapping(path = "/partida/{id}")
    public Partida updatePartida(@RequestBody Partida partida, @PathVariable("id") Integer id) {
        return service.updatePartidaById(partida, id);
    }

    // Patch - atualiza uma coluna especifica
    @PatchMapping(path = "/partida/{id}")
    public ResponseEntity<Partida> updatePartidaInformacoes(@RequestBody Partida partida,@PathVariable("id") Integer id) {

        Optional<Partida> currentPartida = service.findById(id);
        Partida updatePartidaData = new Partida();
        if (currentPartida.isEmpty()) {
            throw new RecordNotFoundException("Partida " + id + "não existe");
        } else {
            updatePartidaData = currentPartida.get();
            if (partida.getClubeMandante() != null && partida.getClubeMandante() != "") {
               updatePartidaData.setClubeMandante(partida.getClubeMandante());
            }
            if (partida.getClubeConvidado() != null && partida.getClubeConvidado() != "") {
                updatePartidaData.setClubeConvidado(partida.getClubeConvidado());
            }
            if (partida.getData() != null) {
                updatePartidaData.setData(String.valueOf(partida.getData()));
            }
            if (partida.getHorario() != null) {
                updatePartidaData.setHorario(String.valueOf(partida.getData()));
            }
            if (partida.getEstadio() != null && !partida.getEstadio().equals("")) {
                updatePartidaData.setEstadio(partida.getEstadio());
            }
            return new ResponseEntity<Partida>(service.save(updatePartidaData), HttpStatus.OK);
        }
    }

    // Deletar uma partida
    // http://localhost:8080/partida/1
    @DeleteMapping(path = "/partida/{id}")
    public ResponseEntity<String> deletePartida(@PathVariable("id") Integer id) {
        boolean isUserExists = service.checkUserExists(id);

        if (!isUserExists) {
            throw new RecordNotFoundException("Partida " + id + " não existe");
        } else {
            service.deletePartidaById(id);
        }
        return new ResponseEntity<String>("Partida " + id + " foi excluída", HttpStatus.OK);
    }

    //Filtrar partidas que terminaram com goleada
    //http://localhost:8081/partida/goleadas
    @GetMapping("/partida/goleadas")
    public ResponseEntity<List<Partida>> buscarPartidaGoleada(@RequestBody PartidaDTO partidaDTO){
        List<Partida> goleadas = service.buscarPartidaGoleada(partidaDTO);
        return ResponseEntity.ok(goleadas);
    }

    //Filtrar partidas que terminaram 0 a 0
    //http://localhost:8081/partida/goleadas
    @GetMapping("/partida/semGols")
    public ResponseEntity<List<Partida>> buscarPartidaSemGols(@RequestBody PartidaDTO partidaDTO){
        List<Partida> semGols = service.buscarPartidaSemGols(partidaDTO);
        return ResponseEntity.ok(semGols);
    }

    //Filtrar todas as partidas do time e se era mandante ou visitante
    ///http://localhost:8081/partida/time
    @GetMapping("/partida/timeMandanteConvidado")
    public ResponseEntity<List<Partida>> buscarTimeMandanteConvidado(@RequestBody PartidaDTO partidaDTO){
        List<Partida> time = service.buscarTimeMandanteConvidado(partidaDTO);
        return ResponseEntity.ok(time);
    }

}
