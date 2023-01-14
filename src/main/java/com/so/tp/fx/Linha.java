package com.so.tp.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Linha {
    private int numero;
    String nome;
    private List<Estacao> estacoes;
    private Semaphore semaphore;
    private List<Comboio> trains;

    public Linha(int numero, String nome) {
        this.numero = numero;
        this.estacoes = null;
        this.nome = nome;
        this.semaphore = new Semaphore(1);
        this.trains = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estacao> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(List<Estacao> estacoes) {
        this.estacoes = estacoes;
    }

    public Estacao getEstacaoSeguinte(Estacao estacao) {
        int index = estacoes.indexOf(estacao);
        if (index < estacoes.size() - 1) {
            return estacoes.get(index + 1);
        } else {
            return null;
        }
    }

    public Estacao getEstacaoAnterior(Estacao estacao) {
        int index = estacoes.indexOf(estacao);
        if (index > 0) {
            return estacoes.get(index - 1);
        } else {
            return null;
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public List<Comboio> getTrains() {
        return trains;
    }

    public void setTrains(List<Comboio> trains) {
        this.trains = trains;
    }

}
