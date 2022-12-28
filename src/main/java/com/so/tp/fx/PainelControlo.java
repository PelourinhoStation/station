package com.so.tp.fx;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PainelControlo {

    public static void getDados() throws IOException, ClassNotFoundException{

        List<Estacao> estacoesLinhaPorto1 = new LinkedList<>();
        List<Estacao> estacoesLinhaPorto2 = new LinkedList<>();
        List<Estacao> estacoesLinhaPorto3 = new LinkedList<>();

        Map<Integer, Linha> linhas = new HashMap<>();

        List<Horario> horariosLinhaPorto1 = new LinkedList<>();
        List<Horario> horariosLinhaPorto2 = new LinkedList<>();
        List<Horario> horariosLinhaPorto3 = new LinkedList<>();

        try {
            // Carregue o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crie a conexão com a base de dados
            String url = "jdbc:mysql://localhost/station";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            // get estacoes
            try {
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT estacoes.numero, estacoes.nome, estacoes.lotacao, linhas.nome as nomeLinha FROM estacoes, linhas_estacoes, linhas WHERE estacoes.numero = linhas_estacoes.id_estacao AND linhas.numero = linhas_estacoes.id_linha";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String nome = rs.getString("nome");
                    String nomeLinha = rs.getString("nomeLinha");
                    int lotacao = rs.getInt("lotacao");
                    Estacao estacao = new Estacao(numero, nome, lotacao);

                    switch (nomeLinha) {
                        case "Porto_1" -> estacoesLinhaPorto1.add(estacao);
                        case "Porto_2" -> estacoesLinhaPorto2.add(estacao);
                        case "Porto_3" -> estacoesLinhaPorto3.add(estacao);
                    }
                }
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get estacoes

            // get linhas
            try {
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT * FROM `linhas`";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String nome = rs.getString("nome");

                    if (nome.equals("Porto_1")) {
                        Linha linha = new Linha(numero, estacoesLinhaPorto1);
                        linhas.put(numero, linha);
                    } else if (nome.equals("Porto_2")) {
                        Linha linha = new Linha(numero, estacoesLinhaPorto2);
                        linhas.put(numero, linha);
                    } else if (nome.equals("Porto_3")) {
                        Linha linha = new Linha(numero, estacoesLinhaPorto3);
                        linhas.put(numero, linha);
                    }

                }
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get linhas

            // get horarios
            try {
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT horarios.numero, horarios.sentido, horarios.horaPartida, horarios.horaChegada, linhas.numero AS idLinha , linhas.nome AS nomeLinha FROM horarios, linhas WHERE horarios.id_linha = linhas.numero";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String sentido = rs.getString("sentido");
                    String nomeLinha = rs.getString("nomeLinha");
                    String horaPartida = rs.getString("horaPartida");
                    String horaChegada = rs.getString("horaChegada");
                    int idLinha = rs.getInt("idLinha");

                    Horario horario = new Horario(horaPartida, horaChegada, linhas.get(idLinha), sentido);

                    switch (nomeLinha) {
                        case "Porto_1" -> horariosLinhaPorto1.add(horario);
                        case "Porto_2" -> horariosLinhaPorto2.add(horario);
                        case "Porto_3" -> horariosLinhaPorto3.add(horario);
                    }

                }
                stmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get horarios

            // Feche a conexão com a base de dados
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //fim get estacoes

    }

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
