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

    public Comboio(int numero, int lotacao) {
        this.numero = numero;
        this.lotacao = lotacao;
        this.nPassageiros = 0;
        this.estacaoAtual = null;
        this.portasAbertas = false;
        this.semaforo = new Semaphore(1);
        this.passageiros = null;
        this.horarioAtual = null;
        this.horarios = new LinkedList<>();
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

    public void setHorarios(Horario horario) {
        try {
            this.semaforo.acquire();
            this.horarios.add(horario);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.semaforo.release();
        }
    }

    public int getPassageiros() {
        return nPassageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
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
            // Verifica se o comboio est?? sobrelotado
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
        // C??digo para abrir as portas do comboio
        this.portasAbertas = true;
        System.out.println("Comboio " + getNumero() + " abriu as portas na esta????o " + getEstacaoAtual().getNome());
    }

    public void closeDoors() {
        // C??digo para fechar as portas do comboio
        this.portasAbertas = false;
        System.out.println("Comboio " + getNumero() + " fechou as portas na esta????o " + getEstacaoAtual().getNome());
    }

    public void embarcarPassageiro(Passageiro passageiro, Comboio comboio) throws InterruptedException {
        // chama a fun????o isValid da classe bilhete para verificar se o passageiro possui um bilhete v??lido entre as esta????es (exemplo: um passageiro n??o pode entrar num comboio que vai de Lisboa - Porto com um bilhete que vai de Porto - Lisboa)
        if (!passageiro.getBilhete().isValido(passageiro, comboio)) {
            passageiro.setTentouEntrar(true); //?? registado que o passageiro tentou entrar no comboio
        } else if (!passageiro.isEstaNoComboio() && !passageiro.isSaiuDoComboio()) { //sen??o se o passageiro n??o estiver no comboio e n??o tiver sa??do do comboio antes
            if (!this.isOvercrowded()) { //se o comboio n??o estiver sobrelotado
                //verifica o passageiro que tem a viagem menor
                passageiro.setComboio(this); //registar o comboio em que o passageiro est??
                this.addPassenger(passageiro); //adiciona o passageiro ao comboio
                passageiro.setEstaNoComboio(true); //registamos que o passageiro est?? no comboio
                // sout do embarque
                System.out.println("Passageiro " + passageiro.getNome() + " embarcou no comboio " + getNumero() + " na esta????o " + getEstacaoAtual().getNome());
                passageiro.contarEstacoesBilhete(passageiro);
            } else {
                System.out.println("Passageiro " + passageiro.getNome() + " n??o embarcou no comboio " + getNumero() + " na esta????o " + getEstacaoAtual().getNome() + " porque o comboio estava sobrelotado");
            }
        }
    }

    public void desembarcarPassageiro(Passageiro passageiro) throws InterruptedException {
        // verifica se as portas est??o abertas, se a esta????o de sa??da do passageiro ?? igual ?? esta????o atual e se o passageiro est?? no comboio
        if (this.portasAbertas && passageiro.getBilhete().getEstacaoSaida().getNumero() == this.estacaoAtual.getNumero() && passageiro.isEstaNoComboio()) {
            this.removePassenger(passageiro); //remove o passageiro do comboio
            passageiro.setEstaNoComboio(false); //registamos que o passageiro n??o est?? no comboio
            passageiro.setSaiuDoComboio(true); //registamos que o passageiro saiu do comboio
            // sout do desembarque
            System.out.println("Passageiro " + passageiro.getNome() + " desembarcou do comboio " + getNumero() + " na esta????o " + getEstacaoAtual().getNome());
        }
    }

    public void verEntradaPassageiros(Passageiro passageiro) {
        for (int i = 0; i < passageiros.size(); i++) {
            System.out.println("Passageiro " + passageiro.getNome() + "entra na esta????o " + passageiro.getEstacaoEntrada().getNome());
            break;
        }
    }

    public void run() {

        // C??digo para percorrer os horarios atribuidos ao comboio
        for (int i = 0; i < horarios.size(); i++) {
            this.setHorarioAtual(horarios.get(i)); //atribui o hor??rio atual ao comboio
            Linha line = horarios.get(i).getLinha(); //get da linha do hor??rio atual
            Estacao currentStation;

            if (horarios.get(i).getSentido().equals("Ida")) { //se o sentido da linha for "Ida"
                currentStation = line.getEstacoes().get(0); //get da primeira esta????o da linha
            } else { // se o sentido da linha for "Volta"
                currentStation = line.getEstacoes().get(line.getEstacoes().size() - 1); // get da ??ltima esta????o da linha
            }

            setEstacaoAtual(currentStation); //atribui a esta????o atual ao comboio
            String sentido = horarios.get(i).getSentido(); //get do sentido do hor??rio atual


            if (sentido.equals("Ida")) {
                System.out.println("Comboio " + getNumero() + " vai partir da esta????o " + getEstacaoAtual().getNome() + " ??s " + horarios.get(i).getHoraPartida() + "h com destino a " + horarios.get(i).getLinha().getEstacoes().get(horarios.get(i).getLinha().getEstacoes().size() - 1).getNome() + " e com chegada prevista ??s " + horarios.get(i).getHoraChegada() + "h para fazer a linha " + horarios.get(i).getLinha().getNumero() + " no sentido de " + horarios.get(i).getSentido());
            } else {
                System.out.println("Comboio " + getNumero() + " vai partir da esta????o " + getEstacaoAtual().getNome() + " ??s " + horarios.get(i).getHoraPartida() + "h com destino a " + horarios.get(i).getLinha().getEstacoes().get(0).getNome() + " e com chegada prevista ??s " + horarios.get(i).getHoraChegada() + "h para fazer a linha " + horarios.get(i).getLinha().getNumero() + " no sentido de " + horarios.get(i).getSentido());
            }

            // C??digo para percorrer as esta????es da linha
            for (Estacao estacao : line.getEstacoes()) {

                // Verifica se a esta????o atual est?? sobrelotada de comboios
                if (currentStation.isFull()) {
                    //System.out.println("Conflito na esta????o " + currentStation.getNumero() + ": esta????o sobrelotada de comboios");
                }

                //adicionar comboio a esta????o
                try {
                    currentStation.addTrain(this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Abre as portas do comboio a cada 5 segundos
                try {
                    this.openDoors();

                    for (Passageiro passageiro : passageiros) {
                        if (passageiro.getEstacaoEntrada().getNumero() == currentStation.getNumero() && passageiro.getComboio() == null && passageiro.getBilhete().getLinha() == this.getHorarioAtual().getLinha() && !passageiro.isTentouEntrar() && !passageiro.isEstaNoComboio() && !passageiro.isSaiuDoComboio()) {
                            this.embarcarPassageiro(passageiro, this);
                        }
//                        if (passageiro.getBilhete().getEstacaoEntra().getNumero() == currentStation.getNumero() && passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && !passageiro.isTentouEntrar()) {
//                            this.embarcarPassageiro(passageiro, this);
//                        } else {
//                            if (passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && passageiro.getEstacaoEntrada().getNumero() == currentStation.getNumero() && !passageiro.isTentouEntrar()) {
//                                passageiro.setComboio(null); //se o bilhete for inv??lido, o passageiro ?? removido do comboio
//                                passageiro.setTentouEntrar(true); //?? registado que o passageiro tentou entrar no comboio
//                                // sout do conflito
//                                System.out.println("Conflito no comboio " + getNumero() + ": passageiro " + passageiro.getNome() + " tentou embarcar na esta????o errada, devia entrar em: " + passageiro.getBilhete().getEstacaoEntra().getNome());
//                            }
//                        }
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Fecha as portas do comboio por 2 segundos
                try {
                    //verifica se h?? passageiros no comboio que querem sair na esta????o atual
                    for (Passageiro passageiro : passageiros) {
                        // o passgeiro s?? entre se a linha que tem no seu bilhete for igual ?? linha que o comboio est?? a precorrer.
                        if (passageiro.getBilhete().getEstacaoSaida().getNumero() == currentStation.getNumero() && passageiro.getBilhete().getLinha().getNumero() == line.getNumero() && passageiro.isEstaNoComboio()) {
                            this.desembarcarPassageiro(passageiro);
                        }
                    }
                    this.closeDoors();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Verifica se o comboio est?? sobrelotado
                if (isOvercrowded()) {
                    System.out.println("Conflito no comboio " + getNumero() + ": comboio sobrelotado");
                }

                Estacao nextStation;

                // Verifica se a pr??xima esta????o est?? sobrelotada
                if (horarios.get(i).getSentido().equals("Ida")) {
                    nextStation = line.getEstacaoSeguinte(currentStation);
                } else {
                    nextStation = line.getEstacaoAnterior(currentStation);
                }

                //remove comboio da esta????o
                System.out.println("Comboio " + getNumero() + " a sair da esta????o " + currentStation.getNumero() + "-" + currentStation.getNome() + "...");
                currentStation.removeTrain();

                // Atualiza a esta????o atual
                currentStation = nextStation;
                setEstacaoAtual(currentStation);

                //informa????es para o utilizador
                System.out.println("Passageiros a bordo no comboio " + this.getNumero() + ": " + this.getPassageiros());
                System.out.println("\n");
            }
        }
        System.out.println("Comboio " + getNumero() + " completou os seus hor??rios.");
    }
}
