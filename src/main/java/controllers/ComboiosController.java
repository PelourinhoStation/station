package controllers;

import com.so.tp.fx.Comboio;
import com.so.tp.fx.Main;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ComboiosController {
    @FXML
    private TableView<Comboio> tblComboio;
    private TableColumn<Comboio, Integer> colNumero;
    private TableColumn<Comboio, Integer> colLotacao;

    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtLotacao;
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

    public void initialize() throws IOException, ClassNotFoundException {
        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colLotacao = new TableColumn<>("Lotação");
        colLotacao.setCellValueFactory(new PropertyValueFactory<>("lotacao"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Comboio> selectionModel =
                tblComboio.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblComboio.getColumns().addAll(colNumero, colLotacao);

        getDataToTableView();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.comboios.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblComboio.setItems(FXCollections.observableArrayList(PainelControlo.comboios));
    }


    Comboio selectedItem = null;

    public void onRowSelect() {
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblComboio.getSelectionModel().getSelectedItem() != null) {
            // Obtenha o item selecionado
            selectedItem = tblComboio.getSelectionModel().getSelectedItem();
            // Atualize os campos de entrada com os valores do item selecionado
            txtNumero.setText(String.valueOf(selectedItem.getNumero()));
            txtLotacao.setText(String.valueOf(selectedItem.getLotacao()));

            //Main.comboiosParaPartir.add(selectedItem);
        }
    }

    public void onNovoClick() {
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);

        txtNumero.setText("");
        txtLotacao.setText("");
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.insertComboios(Integer.parseInt(txtLotacao.getText()));
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.updateComboios(Integer.parseInt(txtNumero.getText()), Integer.parseInt(txtLotacao.getText()));
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(Integer.parseInt(txtNumero.getText()), "comboios");
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }

    public void onPartirClick() {
        System.out.println("Comboio " + selectedItem.getNumero() + " está selecionado para partir");
        Main.comboiosParaPartir.add(selectedItem);
//        for (Comboio comboio : Main.comboiosParaPartir) {
//            System.out.println(comboio.getNumero());
//        }
//        System.out.println("\n");
    }
}
