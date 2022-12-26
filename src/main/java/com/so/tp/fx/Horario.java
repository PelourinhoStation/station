package com.so.tp.fx;

public class Horario {

    String horaChegada, horaPartida;
    boolean chegada;
    Linha linha;
    String sentido;

    public Horario(String horaPartida, String horaChegada, Linha linha) {
        this.horaChegada = horaChegada;
        this.horaPartida = horaPartida;
        this.linha = linha;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public boolean isChegada() {
        return chegada;
    }

    public void setChegada(boolean chegada) {
        this.chegada = chegada;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

}
