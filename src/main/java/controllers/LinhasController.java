package controllers;

import com.so.tp.fx.Comboio;
import com.so.tp.fx.Horario;
import com.so.tp.fx.Linha;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class LinhasController {

    @FXML
    private TableView<Linha> tblLinha;
    private TableColumn<Linha, Integer> colNumero;
    private TableColumn<Linha, Integer> colNome;

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

    public void initialize() throws IOException, ClassNotFoundException {
        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Linha> selectionModel =
                tblLinha.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblLinha.getColumns().addAll(colNumero, colNome);

        getDataToTableView();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.linhas.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblLinha.setItems(FXCollections.observableArrayList(PainelControlo.linhas));
    }

    public void onRowSelect(){
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblLinha.getSelectionModel().getSelectedItem() != null) {
            // Obtenha o item selecionado
            Linha selectedItem = tblLinha.getSelectionModel().getSelectedItem();
            // Atualize os campos de entrada com os valores do 'item' selecionado
            txtNumero.setText(String.valueOf(selectedItem.getNumero()));
            txtNome.setText(selectedItem.getNome());
        }
    }

    public void onNovoClick(){
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);

        txtNumero.setText("");
        txtNome.setText("");
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.insertLinhas(txtNome.getText());
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.updateLinhas(Integer.parseInt(txtNumero.getText()), txtNome.getText());
            getDataToTableView();
        } catch (Exception e){
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(Integer.parseInt(txtNumero.getText()), "linhas");
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }

}
