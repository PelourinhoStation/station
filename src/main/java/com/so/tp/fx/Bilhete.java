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
        if (passageiro.getBilhete().getEstacaoEntra().getNome().equals(comboio.getEstacaoAtual().getNome())) {

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

    public static class ModelBilhete {
        int numero;
        int idEstacaoEntra;
        String estacaoEntra;
        int idEstacaoSaida;
        String estacaoSaida;
        int idLinha;
        String nomeLinha;
        String sentido;

        public ModelBilhete(int numero, int idEstacaoEntra, String estacaoEntra, int idEstacaoSaida, String estacaoSaida, int idLinha, String nomeLinha, String sentido) {
            this.numero = numero;
            this.idEstacaoEntra = idEstacaoEntra;
            this.estacaoEntra = estacaoEntra;
            this.idEstacaoSaida = idEstacaoSaida;
            this.estacaoSaida = estacaoSaida;
            this.idLinha = idLinha;
            this.nomeLinha = nomeLinha;
            this.sentido = sentido;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public int getIdEstacaoEntra() {
            return idEstacaoEntra;
        }

        public void setIdEstacaoEntra(int idEstacaoEntra) {
            this.idEstacaoEntra = idEstacaoEntra;
        }

        public String getEstacaoEntra() {
            return estacaoEntra;
        }

        public void setEstacaoEntra(String estacaoEntra) {
            this.estacaoEntra = estacaoEntra;
        }

        public int getIdEstacaoSaida() {
            return idEstacaoSaida;
        }

        public void setIdEstacaoSaida(int idEstacaoSaida) {
            this.idEstacaoSaida = idEstacaoSaida;
        }

        public String getEstacaoSaida() {
            return estacaoSaida;
        }

        public void setEstacaoSaida(String estacaoSaida) {
            this.estacaoSaida = estacaoSaida;
        }

        public int getIdLinha() {
            return idLinha;
        }

        public void setIdLinha(int idLinha) {
            this.idLinha = idLinha;
        }

        public String getNomeLinha() {
            return nomeLinha;
        }

        public void setNomeLinha(String linha) {
            this.nomeLinha = linha;
        }

        public String getSentido() {
            return sentido;
        }

        public void setSentido(String sentido) {
            this.sentido = sentido;
        }

    }
}
