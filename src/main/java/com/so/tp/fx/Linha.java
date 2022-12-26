package com.so.tp.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Linha {
    private int numero;
    private String sentido;
    private List<Estacao> estacoes;
    private Semaphore semaphore;
    private List<Comboio> trains;

    public Linha(int numero, String sentido, List<Estacao> estacoes) {
        this.numero = numero;
        this.sentido = sentido;
        this.estacoes = estacoes;
        this.semaphore = new Semaphore(1);
        this.trains = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
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

    public boolean hasConflict() {
        try {
            semaphore.acquire();
            return trains.size() > 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return false;
    }

    public void addTrain(Comboio comboio) {
        try {
            semaphore.acquire();
            trains.add(comboio);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public void removeTrain(Comboio comboio) {
        try {
            semaphore.acquire();
            trains.remove(comboio);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
