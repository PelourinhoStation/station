package controllers;

import com.so.tp.fx.Estacao;
import com.so.tp.fx.Horario;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class horariosLinhasController {
    @FXML
    private TableView<Horario.HorariosLinhas> tblLinhasHorarios;
    private TableColumn<Horario.HorariosLinhas, Integer> colNumero;
    private TableColumn<Horario.HorariosLinhas, Integer> colNumeroHorario;
    private TableColumn<Horario.HorariosLinhas, String> colHoraPartida;
    private TableColumn<Horario.HorariosLinhas, String> colHoraChegada;
    private TableColumn<Horario.HorariosLinhas, String> colNomeLinha;
    private TableColumn<Horario.HorariosLinhas, String> colSentido;

    @FXML
    private ComboBox cbLinha;
    @FXML
    private ComboBox cbHorario;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnNovo;

    public static int nRegisto = 0;

    public void initialize() throws IOException, ClassNotFoundException {
        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNomeLinha = new TableColumn<>("Nome Linha");
        colNomeLinha.setCellValueFactory(new PropertyValueFactory<>("nomeLinha"));
        colNumeroHorario = new TableColumn<>("Número Horario");
        colNumeroHorario.setCellValueFactory(new PropertyValueFactory<>("numHorario"));
        colHoraPartida = new TableColumn<>("Hora Partida");
        colHoraPartida.setCellValueFactory(new PropertyValueFactory<>("horaPartida"));
        colHoraChegada = new TableColumn<>("Hora Chegada");
        colHoraChegada.setCellValueFactory(new PropertyValueFactory<>("horaChegada"));
        colSentido = new TableColumn<>("Sentido");
        colSentido.setCellValueFactory(new PropertyValueFactory<>("sentido"));


        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Horario.HorariosLinhas> selectionModel =
                tblLinhasHorarios.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblLinhasHorarios.getColumns().addAll(colNumero, colNomeLinha, colNumeroHorario, colHoraPartida, colHoraChegada, colSentido);

        getDataToTableView();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.linhasEstacoes.clear();
        PainelControlo.getDados();
        //adicionar os dados à combobox
        initializeComboBox();
        //adicionar dados à tabela
        tblLinhasHorarios.setItems(FXCollections.observableArrayList(PainelControlo.horariosLinhas));
    }

    public void initializeComboBox() {
        cbLinha.getItems().clear();
        cbHorario.getItems().clear();

        for (int i = 0; i < PainelControlo.linhas.size(); i++) {
            cbLinha.getItems().add(PainelControlo.linhas.get(i).getNumero() + " - " + PainelControlo.linhas.get(i).getNome());
        }

        for (int i = 0; i < PainelControlo.horarios.size(); i++) {
            cbHorario.getItems().add(PainelControlo.horarios.get(i).getNumero() + " - " + PainelControlo.horarios.get(i).getHoraPartida() + " às " + PainelControlo.horarios.get(i).getHoraChegada() + " - " + PainelControlo.horarios.get(i).getSentido());
        }
    }

    public void onRowSelect() {
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblLinhasHorarios.getSelectionModel().getSelectedItem() != null) {
            Horario.HorariosLinhas horarioLinha = tblLinhasHorarios.getSelectionModel().getSelectedItem();
            cbLinha.getSelectionModel().select(horarioLinha.getNumLinha() - 1);
            cbHorario.getSelectionModel().select(horarioLinha.getNumHorario() - 1);
            nRegisto = horarioLinha.getNumero();
        }
    }

    public void onNovoClick() {
        cbLinha.getSelectionModel().clearSelection();
        cbHorario.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);
        cbHorario.requestFocus();
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {

        try {
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);
            System.out.println(idLinha);

            String horario = cbHorario.getValue().toString();
            int idHorario = Integer.parseInt(horario.split(" - ")[0]);
            System.out.println(idHorario);

            PainelControlo.insertLinhasHorarios(idLinha, idHorario);
            getDataToTableView();
        } catch (Exception e) {
            System.out.println("Erro ao guardar");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {

            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);
            System.out.println(idLinha);

            String horario = cbHorario.getValue().toString();
            int idHorario = Integer.parseInt(horario.split(" - ")[0]);
            System.out.println(idHorario);

            PainelControlo.updateHorariosLinhas(nRegisto, idLinha, idHorario);

            getDataToTableView();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(nRegisto, "horarios_linhas");
            getDataToTableView();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar");
        }
    }
}
