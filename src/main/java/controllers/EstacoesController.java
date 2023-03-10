package controllers;

import com.so.tp.fx.Estacao;
import com.so.tp.fx.PainelControlo;
import com.so.tp.fx.Passageiro;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class EstacoesController {

    @FXML
    private TableView<Estacao> tblEstacoes;
    private TableColumn<Estacao, Integer> colNumero;
    private TableColumn<Estacao, Integer> colLotacao;
    private TableColumn<Estacao, String> colNome;

    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtNome;
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
        colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLotacao = new TableColumn<>("Lotação");
        colLotacao.setCellValueFactory(new PropertyValueFactory<>("lotacao"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Estacao> selectionModel =
                tblEstacoes.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblEstacoes.getColumns().addAll(colNumero, colNome, colLotacao);

        getDataToTableView();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.estacoes.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblEstacoes.setItems(FXCollections.observableArrayList(PainelControlo.estacoes));
    }

    public void onRowSelect(){
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblEstacoes.getSelectionModel().getSelectedItem() != null) {
            // Obtenha o item selecionado
            Estacao selectedItem = tblEstacoes.getSelectionModel().getSelectedItem();
            // Atualize os campos de entrada com os valores do 'item' selecionado
            txtNumero.setText(String.valueOf(selectedItem.getNumero()));
            txtNome.setText(selectedItem.getNome());
            txtLotacao.setText(String.valueOf(selectedItem.getLotacao()));
        }
    }

    public void onNovoClick(){
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);

        txtNumero.setText("");
        txtNome.setText("");
        txtLotacao.setText("");
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.insertEstacoes(txtNome.getText(), Integer.parseInt(txtLotacao.getText()));
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.updateEstacoes(Integer.parseInt(txtNumero.getText()), txtNome.getText(), Integer.parseInt(txtLotacao.getText()));
            getDataToTableView();
        } catch (Exception e){
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(Integer.parseInt(txtNumero.getText()), "estacoes");
            getDataToTableView();
        } catch (Exception e){
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }
}
