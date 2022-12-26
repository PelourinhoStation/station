package com.so.tp.fx;

import java.util.concurrent.Semaphore;

public class Estacao {
    private int numero;
    private String nome;
    private int lotacao;
    private int passageiros;


    public Estacao(int numero, String nome, int lotacao) {
        this.numero = numero;
        this.nome = nome;
        this.lotacao = lotacao;
        this.passageiros = 0;
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

    public boolean isOvercrowded() {
        return passageiros >= lotacao;
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
