package com.example.timao.model;

import com.example.timao.validation.constraints.annotations.PartidaAnnotation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "partidaData")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Nome do clube mandante precisa ser informado")
    private String clubeMandante;

    @Column
    @NotBlank(message = "Nome do clube visitante precisa ser informado")
    private String clubeConvidado;

    @Column
    @Min(0)
    @NotNull
    private Integer resultadoMandante;

    @Column
    @Min(0)
    @NotNull
    private Integer resultadoConvidado;

    @Column
    private LocalDate data;

    @Column
    @PartidaAnnotation
    private LocalTime horario;

    @Column
    @NotBlank(message = "estadio precisa ser informado")
    private String estadio;


    public Partida() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClubeMandante(String clubeMandante) {
        this.clubeMandante = clubeMandante;
    }

    public void setClubeConvidado(String clubeConvidado) {
        this.clubeConvidado = clubeConvidado;
    }

    public void setResultadoMandante(Integer resultadoMandante) {
        this.resultadoMandante = resultadoMandante;
    }

    public void setResultadoConvidado(Integer resultadoConvidado) {
        this.resultadoConvidado = resultadoConvidado;
    }

    public void setData(String data) {
        this.data = LocalDate.parse(data);
    }

    public void setHorario(String horario) {
        this.horario = LocalTime.parse(horario);
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

}

