package controllers;

import com.so.tp.fx.Bilhete;
import com.so.tp.fx.PainelControlo;
import com.so.tp.fx.Passageiro;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class PassageirosController {
    @FXML
    private TableView<Passageiro.PassegeiroModel> tblPassageiro;
    private TableColumn<Passageiro.PassegeiroModel, Integer> colNumero;
    private TableColumn<Passageiro.PassegeiroModel, String> colNome;
    private TableColumn<Passageiro.PassegeiroModel, String> colEstacaoEntrada;
    private TableColumn<Passageiro.PassegeiroModel, String> colEstacaoSaida;
private TableColumn<Passageiro.PassegeiroModel, String> colEstacaoPassageiro;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtNome;
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
    private ComboBox cbEntrada;
    @FXML
    private ComboBox cbBilhete;

    public static int nRegisto = 0;

    public void initialize() throws IOException, ClassNotFoundException {

        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        colEstacaoEntrada = new TableColumn<>("Estação entrada");
        colEstacaoEntrada.setCellValueFactory(new PropertyValueFactory<>("estacaoEntrada"));

        colEstacaoSaida = new TableColumn<>("Estação saída");
        colEstacaoSaida.setCellValueFactory(new PropertyValueFactory<>("estacaoSaida"));

        colEstacaoPassageiro = new TableColumn<>("Estação passageiro");
        colEstacaoPassageiro.setCellValueFactory(new PropertyValueFactory<>("estacaoEntradP"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Passageiro.PassegeiroModel> selectionModel =
                tblPassageiro.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblPassageiro.getColumns().addAll(colNumero, colNome, colEstacaoEntrada, colEstacaoSaida, colEstacaoPassageiro);

        getDataToTableView();
        initializeComboBox();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.estacoes.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblPassageiro.setItems(FXCollections.observableArrayList(PainelControlo.passageiros));
    }

    public void initializeComboBox() {
        cbEntrada.getItems().clear();
        cbBilhete.getItems().clear();

        for (int i = 0; i < PainelControlo.estacoes.size(); i++) {
            cbEntrada.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
        }

        for (int i = 0; i < PainelControlo.bilhetes.size(); i++) {
            cbBilhete.getItems().add(PainelControlo.bilhetes.get(i).getNumero() + " - " + PainelControlo.bilhetes.get(i).getEstacaoEntra() + " - " + PainelControlo.bilhetes.get(i).getEstacaoSaida() + " - " + PainelControlo.bilhetes.get(i).getSentido());
        }
    }

    public void onRowSelect() {
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblPassageiro.getSelectionModel().getSelectedItem() != null) {
            Passageiro.PassegeiroModel passageiroModel = tblPassageiro.getSelectionModel().getSelectedItem();

            cbBilhete.getSelectionModel().select(passageiroModel.getIdBilhete() - 1);
            cbEntrada.getSelectionModel().select(passageiroModel.getIdEstacaoEntrada() - 1);
            txtNumero.setText(String.valueOf(passageiroModel.getNumero()));
            txtNome.setText(passageiroModel.getNome());
            nRegisto = passageiroModel.getNumero();
            //nRegisto = horarioLinha.getNumero();
        }
    }

    public void onNovoClick() {
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);
        txtNumero.setText("");
        txtNome.setText("");

        cbEntrada.getSelectionModel().clearSelection();
        cbBilhete.getSelectionModel().clearSelection();
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {

            //obter valores do id do combobox
            String bilhete = cbBilhete.getValue().toString();
            int idBilhete = Integer.parseInt(bilhete.split(" - ")[0]);

            String entrada = cbEntrada.getValue().toString();
            int idEntrada = Integer.parseInt(entrada.split(" - ")[0]);

            PainelControlo.insertPassageiros(txtNome.getText(), idBilhete, idEntrada);

            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            //obter valores do id do combobox
            String bilhete = cbBilhete.getValue().toString();
            int idBilhete = Integer.parseInt(bilhete.split(" - ")[0]);

            String entrada = cbEntrada.getValue().toString();
            int idEntrada = Integer.parseInt(entrada.split(" - ")[0]);

            PainelControlo.updatePassageiros(nRegisto, txtNome.getText(), idEntrada, idBilhete);

            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(nRegisto, "passageiros");
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }
}
