package com.so.tp.fx;

public class Horario {

    int numero;
    String horaChegada, horaPartida;
    boolean chegada;
    Linha linha;
    String sentido;
    int idComboio;

    public Horario(int numero, String horaPartida, String horaChegada, String sentido, int idComboio) {
        this.numero = numero;
        this.horaChegada = horaChegada;
        this.horaPartida = horaPartida;
        this.sentido = sentido;
        this.idComboio = idComboio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public int getIdComboio() {
        return idComboio;
    }

    public void setIdComboio(int idComboio) {
        this.idComboio = idComboio;
    }

    public static class HorariosLinhas {
        int numero;
        int numHorario;
        int numLinha;
        String horaChegada;
        String horaPartida;
        String sentido;
        String nomeLinha;

        public HorariosLinhas(int numero, int numHorario, int numLinha, String horaChegada, String horaPartida, String sentido, String nomeLinha) {
            this.numero = numero;
            this.numHorario = numHorario;
            this.numLinha = numLinha;
            this.horaChegada = horaChegada;
            this.horaPartida = horaPartida;
            this.sentido = sentido;
            this.nomeLinha = nomeLinha;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public int getNumHorario() {
            return numHorario;
        }

        public void setNumHorario(int numHorario) {
            this.numHorario = numHorario;
        }

        public int getNumLinha() {
            return numLinha;
        }

        public void setNumLinha(int numLinha) {
            this.numLinha = numLinha;
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

        public String getSentido() {
            return sentido;
        }

        public void setSentido(String sentido) {
            this.sentido = sentido;
        }

        public String getNomeLinha() {
            return nomeLinha;
        }

        public void setNomeLinha(String nomeLinha) {
            this.nomeLinha = nomeLinha;
        }
    }
}
