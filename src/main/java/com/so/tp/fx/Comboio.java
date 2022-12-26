package com.so.tp.fx;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Comboio extends Thread {
    private int numero;
    private int lotacao;
    private List<Horario> horarios;
    private int nPassageiros;
    private Estacao estacaoAtual;

    private Horario horarioAtual;

    private boolean portasAbertas;

    private Semaphore semaforo;

    private List<Passageiro> passageiros;

    private static List<Comboio> comboios = new LinkedList<>(); // lista de comboios

    public Comboio(int numero, int lotacao, List<Horario> horarios, List<Passageiro> passageiros) {
        this.numero = numero;
        this.lotacao = lotacao;
        this.horarios = horarios;
        this.nPassageiros = 0;
        this.estacaoAtual = null;
        this.portasAbertas = false;
        this.semaforo = new Semaphore(1);
        this.passageiros = passageiros;
        this.horarioAtual = null;
        comboios.add(this);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public Horario getHorarioAtual() {
        return horarioAtual;
    }

    public void setHorarioAtual(Horario horarioAtual) {
        this.horarioAtual = horarioAtual;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getPassageiros() {
        return nPassageiros;
    }

    public void setPassageiros(int nPassageiros) {
        this.nPassageiros = nPassageiros;
    }

    public Estacao getEstacaoAtual() {
        return estacaoAtual;
    }

    public void setEstacaoAtual(Estacao estacaoAtual) {
        this.estacaoAtual = estacaoAtual;
    }

    public boolean isOvercrowded() {
        return nPassageiros >= lotacao;
    }

    public void addPassenger(Passageiro passageiro) throws InterruptedException {
        try {
            semaforo.acquire();
            // Verifica se o comboio está sobrelotado
            if (isOvercrowded()) {
                System.out.println("Conflito no comboio " + getNumero() + ": comboio sobrelotado");
                return;
            } else {
                nPassageiros++;
            }

        } finally {
            semaforo.release();
        }
    }

    public void removePassenger(Passageiro passageiro) throws InterruptedException {
        try {
            semaforo.acquire();
            nPassageiros--;
        } finally {
            semaforo.release();
        }
    }

    public boolean isPortasAbertas() {
        return portasAbertas;
    }

    public void setPortasAbertas(boolean portasAbertas) {
        this.portasAbertas = portasAbertas;
    }

    public void openDoors() {
        // Código para abrir as portas do comboio
        this.portasAbertas = true;
        System.out.println("Comboio " + getNumero() + " abriu as portas na estação " + getEstacaoAtual().getNome());
    }

    public void closeDoors() {
        // Código para fechar as portas do comboio
        this.portasAbertas = false;
        System.out.println("Comboio " + getNumero() + " fechou as portas na estação " + getEstacaoAtual().getNome());
    }

    public void embarcarPassageiro(Passageiro passageiro) throws InterruptedException {
        // chama a função isValid da classe bilhete para verificar se o passageiro possui um bilhete válido entre as estações (exemplo: um passageiro não pode entrar num comboio que vai de Lisboa - Porto com um bilhete que vai de Porto - Lisboa)
        if (!passageiro.getBilhete().isValido(passageiro)) {
            passageiro.setComboio(null); //se o bilhete for inválido, o passageiro é removido do comboio
            passageiro.setTentouEntrar(true); //é registado que o passageiro tentou entrar no comboio
            // sout do conflito
            System.out.println("Conflito no comboio " + getNumero() + ": passageiro " + passageiro.getNome() + " tentou entrar sem bilhete válido");
        } else if (!passageiro.isEstaNoComboio() && !passageiro.isSaiuDoComboio()) { //senão se o passageiro não estiver no comboio e não tiver saído do comboio antes
            if (!this.isOvercrowded()) {
                this.addPassenger(passageiro); //adiciona o passageiro ao comboio
                passageiro.setEstaNoComboio(true); //registamos que o passageiro está no comboio
                // sout do embarque
                System.out.println("Passageiro " + passageiro.getNome() + " embarcou no comboio " + getNumero() + " na estação " + getEstacaoAtual().getNome());
            } else {
                System.out.println("Passageiro " + passageiro.getNome() + " não embarcou no comboio " + getNumero() + " na estação " + getEstacaoAtual().getNome() + "porque o comboio estava sobrelotado");
            }
        }
    }

    public void desembarcarPassageiro(Passageiro passageiro) throws InterruptedException {
        // verifica se as portas estão abertas, se a estação de saída do passageiro é igual à estação atual e se o passageiro está no comboio
        if (this.portasAbertas && passageiro.getBilhete().getEstacaoSaida().getNumero() == this.estacaoAtual.getNumero() && passageiro.isEstaNoComboio()) {
            this.removePassenger(passageiro); //remove o passageiro do comboio
            passageiro.setEstaNoComboio(false); //registamos que o passageiro não está no comboio
            passageiro.setSaiuDoComboio(true); //registamos que o passageiro saiu do comboio
            // sout do desembarque
            System.out.println("Passageiro " + passageiro.getNome() + " desembarcou do comboio " + getNumero() + " na estação " + getEstacaoAtual().getNome());
        }
    }

    public void run() {

        // Código para percorrer os horarios atribuidos ao comboio
        for (Horario horario : horarios) {
            this.setHorarioAtual(horario); //atribui o horário atual ao comboio
            Linha line = horario.getLinha(); //get da linha do horário atual
            Estacao currentStation;

            if (line.getSentido() == "Ida") { //se o sentido da linha for "Ida"
                currentStation = line.getEstacoes().get(0); //get da primeira estação da linha
            } else { // se o sentido da linha for "Volta"
                currentStation = line.getEstacoes().get(line.getEstacoes().size() - 1); // get da última estação da linha
            }

            setEstacaoAtual(currentStation); //atribui a estação atual ao comboio

            // Código para percorrer as estações da linha
            for (Estacao estacao : line.getEstacoes()) {

                System.out.println("Comboio " + getNumero() + " a chegar à estação " + currentStation.getNumero() + "-" + currentStation.getNome() + "...");

                // Verifica se a estação atual está sobrelotada
                if (currentStation.isOvercrowded()) {
                    System.out.println("Conflito na estação " + currentStation.getNumero() + ": estação sobrelotada");
                }
                // Abre as portas do comboio a cada 5 segundos
                try {
                    this.openDoors();

                    //verifica se há passageiros no comboio que querem sair na estação atual
                    for (Passageiro passageiro : passageiros) {
                        if (passageiro.getBilhete().getEstacaoSaida().getNumero() == currentStation.getNumero() && passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && passageiro.isEstaNoComboio()) {
                            this.desembarcarPassageiro(passageiro);
                        }
                    }

                    //verifica se há passageiros na estação que querem entrar no comboio
                    for (Passageiro passageiro : passageiros) {
                        if (passageiro.getBilhete().getEstacaoEntra().getNumero() == currentStation.getNumero() && passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && !passageiro.isTentouEntrar()) {
                            this.embarcarPassageiro(passageiro);
                        }
                    }

                    for (Passageiro passageiro : passageiros) {
                        // atribui o comboio ao passageiro
                        passageiro.setComboio(this);
                        if (passageiro.getBilhete().getEstacaoEntra().getNumero() == currentStation.getNumero() && passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && !passageiro.isTentouEntrar()) {
                            this.embarcarPassageiro(passageiro);
                        } else {
                            if (passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && passageiro.getEstacaoEntrada().getNumero() == currentStation.getNumero() && !passageiro.isTentouEntrar()) {
                                passageiro.setComboio(null); //se o bilhete for inválido, o passageiro é removido do comboio
                                passageiro.setTentouEntrar(true); //é registado que o passageiro tentou entrar no comboio
                                // sout do conflito
                                System.out.println("Conflito no comboio " + getNumero() + ": passageiro " + passageiro.getNome() + " tentou embarcar na estação errada, devia entrar em: " + passageiro.getBilhete().getEstacaoEntra().getNome());
                            }
                        }
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Fecha as portas do comboio por 2 segundos
                try {
//                    for (Passageiro passageiro : passageiros) {
//                        this.desembarcarPassageiro(passageiro);
//                    }
                    this.closeDoors();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Verifica se o comboio está sobrelotado
                if (isOvercrowded()) {
                    System.out.println("Conflito no comboio " + getNumero() + ": comboio sobrelotado");
                }

                Estacao nextStation;

                // Verifica se a próxima estação está sobrelotada
                if (line.getSentido() == "Ida") {
                    nextStation = line.getEstacaoSeguinte(currentStation);
                } else {
                    nextStation = line.getEstacaoAnterior(currentStation);
                }

                if (nextStation != null && nextStation.isOvercrowded()) {
                    System.out.println("Conflito na estação " + nextStation.getNumero() + ": estação sobrelotada");
                }

                // Atualiza a estação atual
                currentStation = nextStation;
                setEstacaoAtual(currentStation);

                //informações para o utilizador
                System.out.println("Passageiros a bordo no comboio " + this.getNumero() + ": " + this.getPassageiros());
                System.out.println("\n");
            }
        }
    }
}
