package com.so.tp.fx;

public class Bilhete {
    int numero;
    Estacao estacaoEntra;
    Estacao estacaoSaida;
    Linha linha;
    public Bilhete(int numero, Estacao estacaoEntra, Estacao estacaoSaida, Linha linha) {
        this.numero = numero;
        this.estacaoEntra = estacaoEntra;
        this.estacaoSaida = estacaoSaida;
        this.linha = linha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Estacao getEstacaoEntra() {
        return estacaoEntra;
    }

    public void setEstacaoEntra(Estacao estacaoEntra) {
        this.estacaoEntra = estacaoEntra;
    }

    public Estacao getEstacaoSaida() {
        return estacaoSaida;
    }

    public void setEstacaoSaida(Estacao estacaoSaida) {
        this.estacaoSaida = estacaoSaida;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public boolean isValido(Passageiro passageiro) {
        if (passageiro.getBilhete().getLinha().getSentido()=="Ida"){
            if (passageiro.getBilhete().getEstacaoEntra().getNumero()-passageiro.getBilhete().getEstacaoSaida().getNumero()<0){
                return true;
            } else {
                return false;
            }
        } else {
            if (passageiro.getBilhete().getEstacaoEntra().getNumero()-passageiro.getBilhete().getEstacaoSaida().getNumero()>0){
                return true;
            } else {
                return false;
            }
        }
    }
}
