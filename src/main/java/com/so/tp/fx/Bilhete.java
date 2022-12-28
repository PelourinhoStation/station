package com.so.tp.fx;

public class Bilhete {
    int numero;
    Estacao estacaoEntra;
    Estacao estacaoSaida;
    Linha linha;
    String sentido;
    public Bilhete(int numero, Estacao estacaoEntra, Estacao estacaoSaida, Linha linha, String sentido) {
        this.numero = numero;
        this.estacaoEntra = estacaoEntra;
        this.estacaoSaida = estacaoSaida;
        this.linha = linha;
        this.sentido = sentido;
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

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public boolean isValido(Passageiro passageiro, Comboio comboio) {

        if (passageiro.getBilhete().getEstacaoEntra().equals(comboio.getEstacaoAtual())) {
            if (passageiro.getBilhete().getSentido().equals(comboio.getHorarioAtual().getSentido())) {
                return true;
            } else {
                System.out.println("Passageiro " + passageiro.getNome() + " tentou embarcar no sentido errado");
                return false;
            }
        } else {
            System.out.println("Passageiro " + passageiro.getNome() + " não tem bilhete válido para esta estação, devia entrar em: " + passageiro.getBilhete().getEstacaoEntra().getNome());
            return false;
        }

//        if (passageiro.getBilhete().getSentido()=="Ida"){
//            if (passageiro.getBilhete().getEstacaoEntra().getNumero()-passageiro.getBilhete().getEstacaoSaida().getNumero()<0){
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            if (passageiro.getBilhete().getEstacaoEntra().getNumero()-passageiro.getBilhete().getEstacaoSaida().getNumero()>0){
//                return true;
//            } else {
//                return false;
//            }
//        }
    }
}
