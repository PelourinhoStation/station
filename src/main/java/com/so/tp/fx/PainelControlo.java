package com.so.tp.fx;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PainelControlo {

    public static Estacao[] estacoes = new Estacao[100];

    public static void criaEstacoes() throws IOException {
        File file = new File("estacoes.txt");
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null) {
                String[] estacao = st.split(", ");
                //estacoes[i] = new Estacao(Integer.parseInt(estacao[0]), Integer.parseInt(estacao[2]), estacao[1]);
                i++;
            }
        }
    }

    //guardar dados de comboios num ficheiro de texto
    public static void guardarEstacoes(Estacao estacao) {
        try (FileWriter fw = new FileWriter("estacoes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(estacao.getNumero() + ", " + estacao.getNome() + ", " + estacao.getLotacao());

            if (!out.checkError()) {
                System.out.printf("Sucesso\n");
                criaEstacoes();
            } else {
                System.out.printf("Erro\n");
            }
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }

    //guardar troços num ficheiro de texto
    public static void guardarTrocos(Linha linha) {
        try (FileWriter fw = new FileWriter("horario.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //out.println(linha.getNumero() + ", " + linha.getEstacao1().getNome() + ", " + linha.getEstacao2().getNome() + ", " + linha.getSentido());

            System.out.printf("Sucesso\n");
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }

    //guardar horários num ficheiro de texto
    public static void guardarHorarios(Horario horario) {
        try (FileWriter fw = new FileWriter("horario.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //out.println(horario.getHoraPartida() + ", " + horario.getHoraChegada() + ", " + horario.getTroco().getNumero());

            System.out.printf("Sucesso\n");
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }

    //guardar dados de comboios num ficheiro de texto
    public static void guardarComboios(Comboio comboio) {
        try (FileWriter fw = new FileWriter("comboios.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //out.println(comboio.getNumero() + " " + comboio.getHorario().getHoraPartida() + " " + comboio.getHorario().getHoraChegada());

            System.out.printf("Sucesso\n");
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }

    public static Estacao[] verDadosEstacoes() {
        return estacoes;
    }

    //ver dados existentes num ficheiro de texto
    public static String verDadosTxt(String ficheiro) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ficheiro)));
    }
}
