package com.so.tp.fx;

import java.util.concurrent.Semaphore;

public class Estacao {
    private int numero;
    private String nome;
    private int lotacao;
    private int passageiros;
    private int numComboios;
    private int maxComboios;

    private Semaphore semaforo;

    public Estacao(int numero, String nome, int lotacao) {
        this.numero = numero;
        this.nome = nome;
        this.lotacao = lotacao;
        this.passageiros = 0;
        this.numComboios = 0;
        this.maxComboios = 1;
        this.semaforo = new Semaphore(1);
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

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public int getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(int passageiros) {
        this.passageiros = passageiros;
    }

    public int getNumComboios() {
        return numComboios;
    }

    public void setNumComboios(int numComboios) {
        this.numComboios = numComboios;
    }

    public int getMaxComboios() {
        return maxComboios;
    }

    public void setMaxComboios(int maxComboios) {
        this.maxComboios = maxComboios;
    }

    public boolean isOvercrowded() {
        return passageiros >= lotacao;
    }

    public boolean isFull() {
        return numComboios >= maxComboios;
    }

    public void addTrain() throws InterruptedException {
     try{
        semaforo.acquire();
        //verificar se a estação está sobrelotada
         if (numComboios == maxComboios){
             System.out.println("A estação " + nome + " está lotada de comboios");
             return;
         } else {
             numComboios++;
             System.out.println("A estação " + getNumero() + " tem " + numComboios + " comboios");
         }
     } finally {
            semaforo.release();
     }
    }

    public void removeTrain() {
        try{
            numComboios--;
            System.out.println("A estação " + getNumero() + " tem " + numComboios + " comboios");
        } finally {
            semaforo.release();
        }
    }

    public void addPassenger(Passageiro passageiro) {
        // Verifica se o comboio está sobrelotado
        if (isOvercrowded()) {
            System.out.println("Conflito no comboio " + getNumero() + ": comboio sobrelotado");
            return;
        }
        passageiros++;
    }

    public void removePassenger(Passageiro passageiro) {
        passageiros--;
    }
}
