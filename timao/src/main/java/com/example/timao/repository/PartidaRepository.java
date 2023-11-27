package com.example.timao.repository;
import com.example.timao.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.example.timao.service.PartidaService;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {
   List<Partida> findByEstadio(String estadio);
   @Query("SELECT p FROM Partida p WHERE ABS(p.resultadoMandante - p.resultadoConvidado) >= 3")
   List<Partida> PartidaGoleadas();

   @Query("SELECT p FROM Partida p WHERE p.estadio = :estadio and p.data = :data ")
   List<Partida> findByEstadioAndDate(String estadio, LocalDate data);

   List<Partida> findByResultadoMandanteEqualsAndResultadoConvidadoEquals(
           int resultadoMandante, int resultadoConvidado
   );
   List<Partida> findByClubeMandante(String clubeMandante);
   List<Partida> findByClubeConvidado(String clubeConvidado);

}
