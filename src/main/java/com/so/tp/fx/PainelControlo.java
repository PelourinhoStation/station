package com.so.tp.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainelControlo {

    // Cria a conexão com a base de dados local
    public static String url = "jdbc:mysql://localhost/station";
    public static String username = "root";
    public static String password = "";

    // estruturas para armazenar dados
    public static ObservableList<Estacao> estacoes = FXCollections.observableArrayList();
    public static ObservableList<Comboio> comboios = FXCollections.observableArrayList();

    public static ObservableList<Linha> linhas = FXCollections.observableArrayList();
    public static ObservableList<Estacao.LinhasEstacoes> linhasEstacoes = FXCollections.observableArrayList();
    public static ObservableList<Horario> horarios = FXCollections.observableArrayList();
    public static ObservableList<Horario.HorariosLinhas> horariosLinhas = FXCollections.observableArrayList();
    public static ObservableList<Bilhete.ModelBilhete> bilhetes = FXCollections.observableArrayList();
    public static ObservableList<Passageiro.PassegeiroModel> passageiros = FXCollections.observableArrayList();

    public static void getDados() throws IOException, ClassNotFoundException {
        try {
            // Carregue o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            //Connection connection = DriverManager.getConnection(urlOnline, usernameOnline, passwordOnline);
            int last = 0;
            // get comboios
            try {
                last = 0;
                comboios.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT * FROM comboios";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int lotacao = rs.getInt("lotacao");

                    Comboio comboio = new Comboio(numero, lotacao);
                    comboios.add(comboio);
                    Main.comboios.put(numero, comboio);
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE comboios AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get comboios

            // get estacoes
            try {
                last = 0;
                estacoes.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT * FROM estacoes";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String nome = rs.getString("nome");
                    int lotacao = rs.getInt("lotacao");

                    Estacao estacao = new Estacao(numero, nome, lotacao);
                    estacoes.add(estacao);
                    Main.estacoes.put(numero, estacao);
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE estacoes AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get estacoes

            // get linhas
            try {
                last = 0;
                linhas.clear();
                Main.linhas.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT * FROM `linhas`";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String nome = rs.getString("nome");
                    Linha linha = new Linha(numero, nome);
                    linhas.add(linha);
                    Main.linhas.put(numero, linha);
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE linhas AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get linhas

            // get linhas - estacoes
            try {
                last = 0;
                linhasEstacoes.clear();
                Main.estacoes1.clear();
                Main.estacoes2.clear();
                Main.estacoes3.clear();
                Main.estacoes4.clear();
                Main.estacoes5.clear();
                Main.estacoes6.clear();
                Main.estacoes7.clear();
                Main.estacoes8.clear();
                Main.estacoes9.clear();
                Main.estacoes10.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT linhas_estacoes.numero, estacoes.numero as numeroEstacao, estacoes.nome, estacoes.lotacao, linhas.numero AS idLinha, linhas.nome AS nomeLinha FROM linhas_estacoes JOIN linhas ON linhas_estacoes.id_linha = linhas.numero JOIN estacoes ON linhas_estacoes.id_estacao = estacoes.numero";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int numeroEstacao = rs.getInt("numeroEstacao");
                    String nome = rs.getString("nome");
                    int lotacao = rs.getInt("lotacao");
                    int idLinha = rs.getInt("idLinha");
                    String nomeLinha = rs.getString("nomeLinha");

                    Estacao.LinhasEstacoes linhaEstacao = new Estacao.LinhasEstacoes(numero, numeroEstacao, nome, lotacao, idLinha, nomeLinha);
                    linhasEstacoes.add(linhaEstacao);

                    switch (idLinha) {
                        case 1:
                            Main.estacoes1.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes1);
                            break;
                        case 2:
                            Main.estacoes2.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes2);
                            break;
                        case 3:
                            Main.estacoes3.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes3);
                            break;
                        case 4:
                            Main.estacoes4.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes4);
                            break;
                        case 5:
                            Main.estacoes5.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes5);
                            break;
                        case 6:
                            Main.estacoes6.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes6);
                            break;
                        case 7:
                            Main.estacoes7.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes7);
                            break;
                        case 8:
                            Main.estacoes8.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes8);
                            break;
                        case 9:
                            Main.estacoes9.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes9);
                            break;
                        case 10:
                            Main.estacoes10.add(Main.estacoes.get(numero));
                            Main.linhas.get(idLinha).setEstacoes(Main.estacoes10);
                            break;
                    }
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE linhas_estacoes AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get linhas - estacoes

            // get horarios
            try {
                last = 0;
                horarios.clear();
                Main.horarios.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT * FROM horarios";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int idComboio = rs.getInt("id_comboio");
                    String sentido = rs.getString("sentido");
                    String horaPartida = rs.getString("horaPartida");
                    String horaChegada = rs.getString("horaChegada");

                    Horario horario = new Horario(numero, horaPartida, horaChegada, sentido, idComboio);
                    horarios.add(horario);
                    Main.horarios.put(numero, horario);

                    Main.comboios.get(idComboio).setHorarios(Main.horarios.get(numero));
                    Main.comboios.get(idComboio).setPassageiros(Main.passageiros);

                    last = numero;
                }
//                System.out.println(Main.horarios.get(1).getNumero());
//                System.out.println(Main.horarios.get(4).getNumero());
//
//                Main.comboios.get(1).setHorarios(Main.horarios.get(3));
//                Main.comboios.get(1).setPassageiros(Main.passageiros);

                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE horarios AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // get horarios - linhas
            try {
                last = 0;
                horariosLinhas.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT horarios_linhas.numero, horarios.numero AS nHorario, horarios.horaPartida, horarios.horaChegada, linhas.numero AS nLinha, linhas.nome, horarios.sentido FROM horarios, linhas, horarios_linhas WHERE horarios.numero = horarios_linhas.id_horario AND linhas.numero = horarios_linhas.id_linha";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int nHorario = rs.getInt("nHorario");
                    int nLinha = rs.getInt("nLinha");
                    String nomeLinha = rs.getString("nome");
                    String horaPartida = rs.getString("horaPartida");
                    String horaChegada = rs.getString("horaChegada");
                    String sentido = rs.getString("sentido");

                    Horario.HorariosLinhas horarioLinha = new Horario.HorariosLinhas(numero, nHorario, nLinha, horaChegada, horaPartida, sentido, nomeLinha);
                    horariosLinhas.add(horarioLinha);

                    //System.out.println(Main.horarios.get(nHorario).getNumero() + " - " + Main.linhas.get(nLinha).getNumero());
                    Main.horarios.get(nHorario).setLinha(Main.linhas.get(nLinha));

//                    switch (nLinha) {
//                        case 1:
//                            Main.horariosLinhaPorto1.add(Main.horarios.get(nHorario));
//                            break;
//                        case 2:
//                            Main.horariosLinhaPorto2.add(Main.horarios.get(nHorario));
//                            break;
//                        case 3:
//                            Main.horariosLinhaPorto3.add(Main.horarios.get(nHorario));
//                            break;
//                    }

                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE horarios_linhas AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get horarios - linhas

            // get bilhetes
            try {
                last = 0;
                bilhetes.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT bilhetes.numero, bilhetes.estacaoEntrada AS idEstacaoEntrada, estacaoEntrada.nome AS estacaoEntrada, bilhetes.estacaoSaida AS idEstacaoSaida, estacaoSaida.nome AS estacaoSaida, bilhetes.idLinha, linhas.nome AS nomeLinha, sentido FROM bilhetes JOIN estacoes AS estacaoEntrada ON bilhetes.estacaoEntrada = estacaoEntrada.numero JOIN estacoes AS estacaoSaida ON bilhetes.estacaoSaida = estacaoSaida.numero JOIN linhas ON bilhetes.idLinha = linhas.numero";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    int idEstacaoEntrada = rs.getInt("idEstacaoEntrada");
                    String estacaoEntrada = rs.getString("estacaoEntrada");
                    int idEstacaoSaida = rs.getInt("idEstacaoSaida");
                    String estacaoSaida = rs.getString("estacaoSaida");
                    int idLinha = rs.getInt("idLinha");
                    String sentido = rs.getString("sentido");
                    String nomeLinha = rs.getString("nomeLinha");

                    Bilhete bilhete = new Bilhete(numero, Main.estacoes.get(idEstacaoEntrada), Main.estacoes.get(idEstacaoSaida), Main.linhas.get(idLinha), sentido);
                    Bilhete.ModelBilhete bilheteModel = new Bilhete.ModelBilhete(numero, idEstacaoEntrada, estacaoEntrada, idEstacaoSaida, estacaoSaida, idLinha, nomeLinha, sentido);
                    bilhetes.add(bilheteModel);
                    Main.bilhetes.put(numero, bilhete);
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE bilhetes AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get bilhetes

            // get passageiros
            try {
                last = 0;
                passageiros.clear();
                // Crie a consulta SQL para selecionar os dados das estações
                String sql = "SELECT passageiros.numero, passageiros.nome, estacaoEntrada.nome AS estacaoEntrada, estacaoSaida.nome as estacaoSaida, estacoes.nome as estacaoEntradaP, passageiros.idBilhete, passageiros.idEstacaoEntrada FROM bilhetes JOIN estacoes AS estacaoEntrada ON bilhetes.estacaoEntrada = estacaoEntrada.numero JOIN estacoes AS estacaoSaida ON bilhetes.estacaoSaida = estacaoSaida.numero JOIN passageiros ON passageiros.idBilhete = bilhetes.numero JOIN estacoes ON passageiros.idEstacaoEntrada = estacoes.numero";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int numero = rs.getInt("numero");
                    String nome = rs.getString("nome");
                    String estacaoEntrada = rs.getString("estacaoEntrada");
                    String estacaoSaida = rs.getString("estacaoSaida");
                    String estacaoEntradP = rs.getString("estacaoEntradaP");
                    int idEstacaoEntrada = rs.getInt("idEstacaoEntrada");
                    int idBilhete = rs.getInt("idBilhete");

                    Passageiro passageiro = new Passageiro(numero, nome, Main.bilhetes.get(idBilhete), Main.estacoes.get(idEstacaoEntrada));
                    Passageiro.PassegeiroModel passageiroModel = new Passageiro.PassegeiroModel(numero, nome, estacaoEntrada, estacaoSaida, estacaoEntradP, idEstacaoEntrada, idBilhete);
                    passageiros.add(passageiroModel);
                    Main.passageiros.add(passageiro);
                    last = numero;
                }
                stmt.close();
                rs.close();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql1 = "ALTER TABLE passageiros AUTO_INCREMENT = ?;";
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    stmt1.setInt(1, last + 1);
                    stmt1.executeUpdate();
                    stmt1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // fim get passageiros

            // Feche a conexão com a base de dados
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean insertEstacoes(String nome, int lotacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO estacoes (nome, lotacao) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, lotacao);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateEstacoes(int numero, String nome, int lotacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE estacoes SET nome = ?, lotacao = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, lotacao);
            stmt.setInt(3, numero);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean insertLinhas(String nome) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO linhas (nome) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateLinhas(int numero, String nome) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE linhas SET nome = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, numero);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void insertLinhasEstacoes(int idLinha, int idEstacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO linhas_estacoes (id_estacao, id_linha) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idEstacao);
            stmt.setInt(2, idLinha);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateLinhasEstacoes(int numero, int idLinha, int idEstacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE linhas_estacoes SET id_estacao = ?, id_linha = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idEstacao);
            stmt.setInt(2, idLinha);
            stmt.setInt(3, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertComboios(int lotacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO comboios (lotacao) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, lotacao);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateComboios(int numero, int lotacao) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE comboios SET lotacao = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, lotacao);
            stmt.setInt(2, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertHorarios(String horaPartida, String horaChegada, String sentido, int idComboio) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO horarios (sentido, horaPartida, horaChegada, id_comboio) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, sentido);
            stmt.setString(2, horaPartida);
            stmt.setString(3, horaChegada);
            stmt.setInt(4, idComboio);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateHorarios(int numero, String horaPartida, String horaChegada, String sentido, int idComboio) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE horarios SET sentido = ?, horaPartida = ?, horaChegada = ?, id_comboio = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, sentido);
            stmt.setString(2, horaPartida);
            stmt.setString(3, horaChegada);
            stmt.setInt(4, idComboio);
            stmt.setInt(5, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertLinhasHorarios(int idLinha, int idHorario) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO horarios_linhas (id_horario, id_linha) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idHorario);
            stmt.setInt(2, idLinha);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateHorariosLinhas(int numero, int idLinha, int idHorario) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE horarios_linhas SET id_horario = ?, id_linha = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idHorario);
            stmt.setInt(2, idLinha);
            stmt.setInt(3, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertBilhetes(int estacaoEntrada, int estacaoSaida, int idLinha, String sentido) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO bilhetes (estacaoEntrada, estacaoSaida, idLinha, sentido) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, estacaoEntrada);
            stmt.setInt(2, estacaoSaida);
            stmt.setInt(3, idLinha);
            stmt.setString(4, sentido);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBilhetes(int numero, int estacaoEntrada, int estacaoSaida, int idLinha, String sentido) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE bilhetes SET estacaoEntrada = ?, estacaoSaida = ?, idLinha = ?, sentido = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, estacaoEntrada);
            stmt.setInt(2, estacaoSaida);
            stmt.setInt(3, idLinha);
            stmt.setString(4, sentido);
            stmt.setInt(5, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPassageiros(String nome, int idBilhete, int idEstacaoEntrada) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO passageiros (nome, idBilhete, idEstacaoEntrada) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setInt(2, idBilhete);
            stmt.setInt(3, idEstacaoEntrada);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePassageiros(int numero, String nome, int idBilhete, int idEstacaoEntrada) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE passageiros SET nome = ?, idBilhete = ?, idEstacaoEntrada = ? WHERE numero = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setInt(2, idBilhete);
            stmt.setInt(3, idEstacaoEntrada);
            stmt.setInt(4, numero);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verfyData(int numero, String tabela) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);

        if (tabela.equals("horarios")) {
            String sql = "SELECT * FROM `horarios_linhas` WHERE id_horario = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, numero);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idHorario = rs.getInt("id_horario");
                if (idHorario == numero) {
                    return false;
                } else {
                    return true;
                }
            }
            stmt.close();
            rs.close();
        }

        return true;
    }

    public static void deleteData(int numero, String tabela) throws SQLException {
        if (verfyData(numero, tabela)) {
            Connection connection = DriverManager.getConnection(url, username, password);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String sql = "DELETE FROM " + tabela + " WHERE numero = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, numero);
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não foi possivel apagar o dado");
        }


    }
}
