package controllers;

import com.so.tp.fx.Bilhete;
import com.so.tp.fx.Estacao;
import com.so.tp.fx.Horario;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class BilhetesController {
    @FXML
    private TableView<Bilhete.ModelBilhete> tblBilhetes;
    private TableColumn<Bilhete.ModelBilhete, Integer> colNumero;
    private TableColumn<Bilhete.ModelBilhete, String> colEstacaoEntrada;
    private TableColumn<Bilhete.ModelBilhete, String> colEstacaoSaida;
    private TableColumn<Bilhete.ModelBilhete, String> colNomeLinha;
    private TableColumn<Bilhete.ModelBilhete, String> colSentido;

    @FXML
    private TextField txtNumero;
    @FXML
    private Label lblAlerta;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnNovo;

    @FXML
    private ComboBox cbLinha;
    @FXML
    private ComboBox cbEntrada;
    @FXML
    private ComboBox cbSaida;

    @FXML
    private ComboBox cbSentido;

    public static int nRegisto = 0;

    public void initialize() throws IOException, ClassNotFoundException {

        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        colEstacaoEntrada = new TableColumn<>("Estação entrada");
        colEstacaoEntrada.setCellValueFactory(new PropertyValueFactory<>("estacaoEntra"));

        colEstacaoSaida = new TableColumn<>("Estação saída");
        colEstacaoSaida.setCellValueFactory(new PropertyValueFactory<>("estacaoSaida"));

        colNomeLinha = new TableColumn<>("Nome linha");
        colNomeLinha.setCellValueFactory(new PropertyValueFactory<>("nomeLinha"));

        colSentido = new TableColumn<>("Sentido");
        colSentido.setCellValueFactory(new PropertyValueFactory<>("sentido"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Bilhete.ModelBilhete> selectionModel =
                tblBilhetes.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblBilhetes.getColumns().addAll(colNumero, colEstacaoEntrada, colEstacaoSaida, colNomeLinha, colSentido);

        getDataToTableView();
        initializeComboBox();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.estacoes.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblBilhetes.setItems(FXCollections.observableArrayList(PainelControlo.bilhetes));
    }

    public void onLineChanged() {
        cbEntrada.getItems().clear();
        cbSaida.getItems().clear();

        if (cbLinha.getValue()!=null){
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);

            for (int i = 0; i < PainelControlo.estacoes.size(); i++) {
                if (PainelControlo.linhas.get(idLinha - 1).getEstacoes().contains(PainelControlo.estacoes.get(i))) {
                    cbEntrada.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
                    cbSaida.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
                }
            }

            cbEntrada.getSelectionModel().selectFirst();
            cbSaida.getSelectionModel().selectLast();
        }
    }

    public void initializeComboBox() {
        cbLinha.getItems().clear();
        cbEntrada.getItems().clear();
        cbSaida.getItems().clear();

        for (int i = 0; i < PainelControlo.linhas.size(); i++) {
            cbLinha.getItems().add(PainelControlo.linhas.get(i).getNumero() + " - " + PainelControlo.linhas.get(i).getNome());
        }

        cbSentido.getItems().addAll("Ida", "Volta");

        for (int i = 0; i < PainelControlo.estacoes.size(); i++) {
            if (PainelControlo.linhas.get(0).getEstacoes().contains(PainelControlo.estacoes.get(i))) {
                cbEntrada.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
                cbSaida.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
            }
        }

        cbLinha.getSelectionModel().selectFirst();
        cbSentido.getSelectionModel().selectFirst();
        cbEntrada.getSelectionModel().selectFirst();
        cbSaida.getSelectionModel().selectLast();
        //cbLinha.setValue(PainelControlo.linhas.get(0).getNumero() + " - " + PainelControlo.linhas.get(0).getNome());

    }

    public void onRowSelect() {
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblBilhetes.getSelectionModel().getSelectedItem() != null) {
            Bilhete.ModelBilhete bilheteModel = tblBilhetes.getSelectionModel().getSelectedItem();
            cbLinha.getSelectionModel().select(bilheteModel.getIdLinha() - 1);
            cbEntrada.getSelectionModel().select(bilheteModel.getIdEstacaoEntra() - 1);
            cbSaida.getSelectionModel().select(bilheteModel.getIdEstacaoSaida() - 1);
            cbSentido.getSelectionModel().select(bilheteModel.getSentido());
            txtNumero.setText(String.valueOf(bilheteModel.getNumero()));
            nRegisto = bilheteModel.getNumero();
            //nRegisto = horarioLinha.getNumero();
        }
    }

    public void onNovoClick() {
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);
        txtNumero.setText("");

        cbLinha.getSelectionModel().clearSelection();
        cbEntrada.getSelectionModel().clearSelection();
        cbSaida.getSelectionModel().clearSelection();
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {

            //obter valores do id do combobox
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);

            String entrada = cbEntrada.getValue().toString();
            int idEntrada = Integer.parseInt(entrada.split(" - ")[0]);

            String saida = cbSaida.getValue().toString();
            int idSaida = Integer.parseInt(saida.split(" - ")[0]);

            PainelControlo.insertBilhetes(idEntrada, idSaida, idLinha, cbSentido.getValue().toString());

            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            //obter valores do id do combobox
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);

            String entrada = cbEntrada.getValue().toString();
            int idEntrada = Integer.parseInt(entrada.split(" - ")[0]);

            String saida = cbSaida.getValue().toString();
            int idSaida = Integer.parseInt(saida.split(" - ")[0]);



            PainelControlo.updateBilhetes(nRegisto, idEntrada, idSaida, idLinha, cbSentido.getValue().toString());
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(nRegisto, "bilhetes");
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }
}
