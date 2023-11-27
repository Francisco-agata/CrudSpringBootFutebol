package com.example.timao.service;


import com.example.timao.dto.PartidaDTO;
import com.example.timao.error.RecordNotFoundException;
import com.example.timao.model.Partida;
import com.example.timao.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {
    @Autowired
    PartidaRepository repository;

    public List<Partida> findAll(){
        return repository.findAll();
    }

    public Partida save(Partida partida){

        if ( !this.repository.findByEstadioAndDate(partida.getEstadio(), partida.getData())
                .isEmpty() ) {
            throw new RecordNotFoundException(
                    String.format("Já existe partida no estádio %s e data %s ", partida.getEstadio(), partida.getData()));
        }

        return repository.save(partida);

    }
    public Optional<Partida> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Partida> findByEstadio(String estadio) {
        return repository.findByEstadio(estadio);
    }

    public boolean checkUserExists(Integer id) {
        return repository.existsById(id);
    }

    public Partida updatePartidaById(Partida partida, Integer id) {
        Partida updatePartida = new Partida();
        updatePartida.setId(id);
        updatePartida.setClubeMandante(partida.getClubeMandante());
        updatePartida.setClubeConvidado(partida.getClubeConvidado());
        updatePartida.setResultadoMandante(Integer.parseInt(String.valueOf(partida.getResultadoMandante())));
        updatePartida.setResultadoConvidado(Integer.parseInt(String.valueOf(partida.getResultadoConvidado())));
        updatePartida.setData(String.valueOf(partida.getData()));
        updatePartida.setHorario(String.valueOf(partida.getHorario()));
        updatePartida.setEstadio(partida.getEstadio());
        return repository.save(updatePartida);
    }

    public boolean deletePartidaById(Integer id) {

        repository.deleteById(id);

        return true;
    }

    public String deleteAll() {
        repository.deleteAll();
        return "Partida deletada!";
    }

    public List<Partida> buscarPartidaGoleada(PartidaDTO partidaDTO){
        return repository.PartidaGoleadas();
    }

    public List<Partida> buscarPartidaSemGols(PartidaDTO partidaDTO){
        return repository.findByResultadoMandanteEqualsAndResultadoConvidadoEquals(
     0,0
        );
    }

    public List<Partida> buscarTimeMandanteConvidado(PartidaDTO partidaDTO) {
        if (
                "mandante".equalsIgnoreCase(partidaDTO.getMandanteConvidado())
        ) {
            return repository.findByClubeMandante(partidaDTO.getTime());
        } else if (
                "convidado".equalsIgnoreCase(partidaDTO.getMandanteConvidado())
        ) {
            return repository.findByClubeConvidado(partidaDTO.getTime());
        }
        return null;
    }
}
