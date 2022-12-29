package com.so.tp.fx;

public class Passageiro {
    private int numero;
    private String nome;
    private Bilhete bilhete;
    private Estacao estacaoEntrada;
    private Comboio comboio;
    private boolean tentouEntrar;
    private boolean estaNoComboio;
    private boolean saiuDoComboio;
    private int numEstacoesBilhete;

    public Passageiro(int numero, String nome, Bilhete bilhete, Estacao estacaoEntrada) {
        this.numero = numero;
        this.nome = nome;
        this.bilhete = bilhete;
        this.estacaoEntrada = estacaoEntrada;
        this.comboio = null;
        this.tentouEntrar = false;
        this.estaNoComboio = false;
        this.saiuDoComboio = false;
        this.numEstacoesBilhete = 0;
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

    public Bilhete getBilhete() {
        return bilhete;
    }

    public void setBilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
    }

    public Estacao getEstacaoEntrada() {
        return estacaoEntrada;
    }

    public void setEstacaoEntrada(Estacao estacaoEntrada) {
        this.estacaoEntrada = estacaoEntrada;
    }

    public Comboio getComboio() {
        return comboio;
    }

    public void setComboio(Comboio comboio) {
        this.comboio = comboio;
    }

    public boolean isTentouEntrar() {
        return tentouEntrar;
    }

    public void setTentouEntrar(boolean tentouEntrar) {
        this.tentouEntrar = tentouEntrar;
    }

    public boolean isEstaNoComboio() {
        return estaNoComboio;
    }

    public void setEstaNoComboio(boolean estaNoComboio) {
        this.estaNoComboio = estaNoComboio;
    }

    public boolean isSaiuDoComboio() {
        return saiuDoComboio;
    }

    public void setSaiuDoComboio(boolean saiuDoComboio) {
        this.saiuDoComboio = saiuDoComboio;
    }

    public int getNumEstacoesBilhete() {
        return numEstacoesBilhete;
    }

    public void setNumEstacoesBilhete(int numEstacoesBilhete) {
        this.numEstacoesBilhete = numEstacoesBilhete;
    }

    public void contarEscaoesBilhete(Passageiro passageiro) {
        setNumEstacoesBilhete(passageiro.getEstacaoEntrada().getNumero() - passageiro.getBilhete().getEstacaoSaida().getNumero());

        if (getNumEstacoesBilhete() < 0) {
            setNumEstacoesBilhete(getNumEstacoesBilhete() * -1);
        }

        System.out.println("O passageiro " + passageiro.getNome() + " vai viajar por " + getNumEstacoesBilhete() + " estações.");
    }

}

