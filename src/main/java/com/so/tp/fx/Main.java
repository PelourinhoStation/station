package com.so.tp.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    public static List<Passageiro> passageiros = new ArrayList<>();
//    public static List<Passageiro> passageirosComboio1 = new LinkedList<>();
//    public static List<Passageiro> passageirosComboio2 = new LinkedList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SISTEMA DE GESTÃO FERROVIÁRIA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        //Estacao estacao = new Estacao(7, 100, "Estação de Lisboa");
        //PainelControlo.guardarEstacoes(estacao);
        //PainelControlo.criaEstacoes();
        //launch();

        geradorDeDados();
    }

    public static void geradorDeDados(){
        // Cria algumas estações
        Estacao estacao1 = new Estacao(1, "Marco Canaveses", 100);
        Estacao estacao2 = new Estacao(2, "Livração", 200);
        Estacao estacao3 = new Estacao(3, "Vila Meã", 300);
        Estacao estacao4 = new Estacao(4, "Oliveira", 400);
        Estacao estacao5 = new Estacao(5, "Caíde", 500);
        Estacao estacao6 = new Estacao(6, "Meinedo", 600);

        Estacao estacao7 = new Estacao(7, "Bustelo", 600);
        Estacao estacao8 = new Estacao(8, "Penafiel", 600);
        Estacao estacao9 = new Estacao(9, "Paredes", 600);
        Estacao estacao10 = new Estacao(10, "Oleiros", 600);
        Estacao estacao11 = new Estacao(11, "Irivo", 500);
        Estacao estacao12 = new Estacao(12, "Cete", 600);

        Estacao estacao13 = new Estacao(13, "Parada", 600);
        Estacao estacao14 = new Estacao(14, "Recarei-Sobreira", 600);
        Estacao estacao15 = new Estacao(15, "Trancoso", 600);
        Estacao estacao16 = new Estacao(16, "Terronhas", 600);
        Estacao estacao17 = new Estacao(17, "S. Martinho Campo", 600);
        Estacao estacao18 = new Estacao(18, "Valongo", 500);

        Estacao estacao19 = new Estacao(19, "Suzão", 600);
        Estacao estacao20 = new Estacao(20, "Cabeda", 600);
        Estacao estacao21 = new Estacao(21, "Ermesinde", 600);
        Estacao estacao22 = new Estacao(22, "Aguas Santas", 600);
        Estacao estacao23 = new Estacao(23, "Rio Tinto", 600);
        Estacao estacao24 = new Estacao(24, "Contumil", 600);

        Estacao estacao25 = new Estacao(25, "Porto - Campanhã", 600);
        Estacao estacao26 = new Estacao(26, "Porto - S.Bento", 600);

        // Guarda as estações num map
        List<Estacao> estacoesLinhaPorto1 = new LinkedList<>();
        estacoesLinhaPorto1.add(estacao1);
        estacoesLinhaPorto1.add(estacao2);
        estacoesLinhaPorto1.add(estacao3);
        estacoesLinhaPorto1.add(estacao4);
        estacoesLinhaPorto1.add(estacao5);
        estacoesLinhaPorto1.add(estacao6);

        List<Estacao> estacoesLinhaPorto2 = new LinkedList<>();
        estacoesLinhaPorto2.add(estacao7);
        estacoesLinhaPorto2.add(estacao8);
        estacoesLinhaPorto2.add(estacao9);
        estacoesLinhaPorto2.add(estacao10);
        estacoesLinhaPorto2.add(estacao11);
        estacoesLinhaPorto2.add(estacao12);

        List<Estacao> estacoesLinhaPorto3 = new LinkedList<>();
        estacoesLinhaPorto3.add(estacao13);
        estacoesLinhaPorto3.add(estacao14);
        estacoesLinhaPorto3.add(estacao15);
        estacoesLinhaPorto3.add(estacao16);
        estacoesLinhaPorto3.add(estacao17);
        estacoesLinhaPorto3.add(estacao18);

        // Cria algumas linhas
        Linha linhaPorto1 = new Linha(1, "Ida", estacoesLinhaPorto1);
        Linha linhaPorto1Volta = new Linha(2, "Volta", estacoesLinhaPorto1);

        Linha linhaPorto2 = new Linha(3, "Ida", estacoesLinhaPorto2);
        Linha linhaPorto2Volta = new Linha(4, "Volta", estacoesLinhaPorto2);

        Linha linhaPorto3 = new Linha(5, "Ida", estacoesLinhaPorto3);

        // Guarda as linhas num map
        Map<Integer, Linha> linhas = new HashMap<>();
        linhas.put(linhaPorto1.getNumero(), linhaPorto1);
        linhas.put(linhaPorto1Volta.getNumero(), linhaPorto1Volta);
        linhas.put(linhaPorto2.getNumero(), linhaPorto2);
        linhas.put(linhaPorto2Volta.getNumero(), linhaPorto2Volta);
        linhas.put(linhaPorto3.getNumero(), linhaPorto3);

        // Cria alguns horários
        Horario horario1 = new Horario("8:00", "9:00", linhaPorto1);
        Horario horario2 = new Horario("9:00", "10:00", linhaPorto1Volta);
        Horario horario3 = new Horario("10:00", "11:00", linhaPorto1);

        Horario horario4 = new Horario("8:00", "9:00", linhaPorto2);
        Horario horario5 = new Horario("9:00", "10:00", linhaPorto2Volta);
        Horario horario6 = new Horario("10:00", "11:00", linhaPorto2);

        Horario horario7 = new Horario("8:00", "9:00", linhaPorto3);
        Horario horario8 = new Horario("9:00", "10:00", linhaPorto3);
        Horario horario9 = new Horario("10:00", "11:00", linhaPorto3);

        // Coloca-os numa lista ligada
        List<Horario> horariosLinhaPorto1 = new LinkedList<>();
        horariosLinhaPorto1.add(horario1);
        //horariosLinhaPorto1.add(horario2);
        //horariosLinhaPorto1.add(horario3);

        List<Horario> horariosLinhaPorto1Volta = new LinkedList<>();
        horariosLinhaPorto1Volta.add(horario2);

        List<Horario> horariosLinhaPorto2 = new LinkedList<>();
        horariosLinhaPorto2.add(horario4);
        //horariosLinhaPorto2.add(horario5);
        //horariosLinhaPorto2.add(horario6);

        List<Horario> horariosLinhaPorto2Volta = new LinkedList<>();
        horariosLinhaPorto2Volta.add(horario5);

        List<Horario> horariosLinhaPorto3 = new LinkedList<>();
        horariosLinhaPorto3.add(horario7);
        //horariosLinhaPorto3.add(horario8);
        //horariosLinhaPorto3.add(horario9);

        // Bilhetes linha1
        Bilhete bilhete1 = new Bilhete(1, estacao1, estacao4, linhaPorto1);
        Bilhete bilhete2 = new Bilhete(2, estacao1, estacao5, linhaPorto1);
        Bilhete bilhete3 = new Bilhete(3, estacao3, estacao6, linhaPorto1);
        Bilhete bilhete4 = new Bilhete(4, estacao4, estacao6, linhaPorto1);
        //Bilhetes linha2
        Bilhete bilhete5 = new Bilhete(5, estacao6, estacao1, linhaPorto1Volta);
        Bilhete bilhete6 = new Bilhete(6, estacao5, estacao2, linhaPorto1Volta);
        Bilhete bilhete7 = new Bilhete(7, estacao3, estacao1, linhaPorto1Volta);
        Bilhete bilhete8 = new Bilhete(8, estacao7, estacao12, linhaPorto2);

        //Passageiros linha1
        Passageiro passageiro1 = new Passageiro(1, "João", bilhete1, estacao1);
        Passageiro passageiro2 = new Passageiro(2, "Maria", bilhete2, estacao1);
        Passageiro passageiro3 = new Passageiro(3, "Pedro", bilhete3, estacao1);
        Passageiro passageiro4 = new Passageiro(4, "Ana", bilhete4, estacao3);
        Passageiro passageiro5 = new Passageiro(5, "Rosa", bilhete1, estacao1);

        passageiros.add(passageiro1);
        passageiros.add(passageiro2);
        passageiros.add(passageiro3);
        passageiros.add(passageiro4);
        passageiros.add(passageiro5);

        //Passageiros linha2
        Passageiro passageiro6 = new Passageiro(6, "Gonçalo", bilhete5, estacao6);
        Passageiro passageiro7 = new Passageiro(7, "Célia", bilhete6, estacao5);
        Passageiro passageiro8 = new Passageiro(8, "Carlos", bilhete7, estacao3);
        Passageiro passageiro9 = new Passageiro(9, "Catarina", bilhete8, estacao7);
        Passageiro passageiro10 = new Passageiro(10, "Joana", bilhete8, estacao7);

        passageiros.add(passageiro6);
        passageiros.add(passageiro7);
        passageiros.add(passageiro8);
        passageiros.add(passageiro9);
        passageiros.add(passageiro10);

        List<Comboio> comboios = new LinkedList<>();

        Comboio comboio1 = new Comboio(1, 100, horariosLinhaPorto1, passageiros);
        Comboio comboio2 = new Comboio(2, 100, horariosLinhaPorto1Volta, passageiros);

        Comboio comboio3 = new Comboio(3, 100, horariosLinhaPorto2, passageiros);
        Comboio comboio4 = new Comboio(4, 100, horariosLinhaPorto2Volta, passageiros);

        comboios.add(comboio1);
        comboios.add(comboio2);
        comboios.add(comboio3);
        comboios.add(comboio4);

        iniciaComboios(comboios);

        //Adicionar um passageiro ao comboio se o bilhetes for válido
        if (comboios != null && !comboios.isEmpty() && passageiros != null && !passageiros.isEmpty()) {
            List<Passageiro> novosPassageiros = new ArrayList<>();

            for (Passageiro passageiro : passageiros) {
                if (passageiro != null && passageiro.getBilhete() != null && passageiro.getBilhete().getLinha() != null) {
                    for (Comboio comboio : comboios) {
                        if (comboio != null && comboio.getHorarioAtual() != null && comboio.getHorarioAtual().getLinha() != null && passageiro.getBilhete().getLinha().getNumero() == comboio.getHorarioAtual().getLinha().getNumero()) {
                            novosPassageiros.add(passageiro);
                            //System.out.println("O passageiro " + passageiro.getNome() + " tem bilhete para o comboio " + comboio.getNumero());
                        }
                    }
                }
            }

            passageiros.addAll(novosPassageiros);
        }
    }

    public static void iniciaComboios(List<Comboio> comboios){

        //instancia do objeto random para gerar um valor aleatório
        Random random = new Random();

        //Guarda numa lista os comboios encontrados em sentido contrario namesma linha
        List<Comboio[]> comboiosEmSentidoContrario = new LinkedList<>();

        //Guarda numa lista os comboios prontos para partir
        List<Comboio> comboiosParaPartir = new LinkedList<>();

        //Variavel auxiliar para ajudar na partida de comboios
        Comboio aux = null;

        //Precorre a lista de comboios para compara-los
        for (int i = 0; i < comboios.size(); i++) { //primeiro ciclo for
            Comboio comboio = comboios.get(i);
            comboio.setHorarioAtual(comboio.getHorarios().get(0));

            boolean encontrouComboioContrario = false;
            /* segundo ciclo for para comparar com o comboio anterior, repare-se que j assume o valor de i+1,
               por essa razão foi necessario usar este ciclo for tradicional */
            for (int j = i + 1; j < comboios.size(); j++) {
                Comboio outroComboio = comboios.get(j);
                outroComboio.setHorarioAtual(outroComboio.getHorarios().get(0));
                //Verifica se as estações que os comboios vão percorrer são iguauis e se o sentido é contrário
                if (comboio.getHorarioAtual().getLinha().getEstacoes().equals(outroComboio.getHorarioAtual().getLinha().getEstacoes()) &&
                        !comboio.getHorarioAtual().getLinha().getSentido().equals(outroComboio.getHorarioAtual().getLinha().getSentido()) && comboio.getNumero() != outroComboio.getNumero()) {

                    aux = outroComboio; //guarda na variavel aux o comboio que esta em sentido contrário
                    comboiosEmSentidoContrario.add(new Comboio[]{comboio, outroComboio});
                    encontrouComboioContrario = true;
                }
            }
            if (!encontrouComboioContrario) {
                if (aux != comboio){ //verifica se está a entrar neste metodo um comboio que já foi dado como estando em sentido contrário
                    comboiosParaPartir.add(comboio); //se não estiver adiciona-o a lista de comboios para partir
                }
            }
        }

        //metodo para percorrer os comboios em sentido contrário
        for (Comboio[] comboiosContrarios : comboiosEmSentidoContrario) {
            int index = random.nextInt(2); //gera um numero aleatorio entre 0 e 1
            Comboio comboio = comboiosContrarios[index]; //se o numero for 0 escolhe o primeiro comboio, se for 1 escolhe o segundo

            System.out.println("\033[31m" + "COMBOIO " + comboio.getNumero() + " E COMBOIO " + comboiosContrarios[1 - index].getNumero() + " EM SENTIDO CONTRÁRIO!" + "\033[0m");
            System.out.println("\033[32m" + "Apenas o comboio " + comboio.getNumero() + " seguiu viagem" + "\033[0m");

            comboiosParaPartir.add(comboio); //adiciona o comboio escolhido a lista de comboios para partir
        }

        //metodo para percorrer os comboios dados como prontos para partir
        for (Comboio comboio : comboiosParaPartir) {
            System.out.println("Comboio " + comboio.getNumero() + " partiu"); //imprime os comboios que vão partir
            comboio.start(); //inicia os comboios que estão na lista
        }
    }
}