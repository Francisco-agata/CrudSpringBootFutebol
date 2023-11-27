package com.example.timao.dto;

import lombok.Getter;

@Getter
public class PartidaDTO {
    private String estadio;
    private int golsMinimos;
    private int semGols;
    private String time;
    private String mandanteConvidado;

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    public void setGolsMinimos(int golsMinimos) {
        this.golsMinimos = golsMinimos;
    }
    public void setSemGols(int semGols) {
        this.semGols = semGols;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setMandanteConvidado(String mandanteConvidado) {
        this.mandanteConvidado = mandanteConvidado;
    }

}
