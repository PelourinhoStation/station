package com.so.tp.fx;

import java.util.concurrent.Semaphore;

public class Estacao {
    private int numero;
    private String nome;
    private int lotacao;
    private int numComboios;
    private int maxComboios;
    private Semaphore semaforo;

    public Estacao(int numero, String nome, int lotacao) {
        this.numero = numero;
        this.nome = nome;
        this.lotacao = lotacao;
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

    public boolean isFull() {
        return numComboios >= maxComboios;
    }

    public void addTrain(Comboio comboio) throws InterruptedException {
     try{
        semaforo.acquire();

        if (numComboios < maxComboios && numComboios + 1 < maxComboios){
             numComboios++;
             System.out.println("A estação " + getNumero() + " tem " + numComboios + " comboios");
         } else if (numComboios + 1 == maxComboios ){
             numComboios++;
             System.out.println("\033[1;31mA estação\033[0m " + nome + " \033[1;31macabou de ficar lotada\033[0m");
         } else {
             System.out.println("\033[1;31mA estação\033[0m " + nome + " \033[1;31mestá lotada de comboios, o comboio\033[0m " + comboio.getNumero() +" \033[1;31maguarda entrada\033[0m");
         }

     } catch (InterruptedException e) {
         throw new RuntimeException(e);
     } //o semaforo não pode ser libertado porque o comboio ainda não saiu da estação, esta instrução apenas deve ser executada quando o comboio sair da estação, ou seja, na função removeTrain
     //} finally {
     //    semaforo.release();
     //}
    }

    public void removeTrain() {
        try{
            numComboios--;
            //System.out.println("A estação " + getNumero() + " tem " + numComboios + " comboios");
        } finally {
            semaforo.release();
        }
    }

    public static class LinhasEstacoes {

        int numero;
        int numeroEstacao;
        String nome;
        int lotacao;
        int idLinha;
        String nomeLinha;

        public LinhasEstacoes(int numero, int numeroEstacao, String nome, int lotacao, int idLinha, String nomeLinha) {
            this.numero = numero;
            this.numeroEstacao = numeroEstacao;
            this.nome = nome;
            this.lotacao = lotacao;
            this.idLinha = idLinha;
            this.nomeLinha = nomeLinha;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public int getNumeroEstacao() {
            return numeroEstacao;
        }

        public void setNumeroEstacao(int numeroEstacao) {
            this.numeroEstacao = numeroEstacao;
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

        public int getIdLinha() {
            return idLinha;
        }

        public void setIdLinha(int idLinha) {
            this.idLinha = idLinha;
        }

        public String getNomeLinha() {
            return nomeLinha;
        }

        public void setNomeLinha(String nomeLinha) {
            this.nomeLinha = nomeLinha;
        }
    }
}
