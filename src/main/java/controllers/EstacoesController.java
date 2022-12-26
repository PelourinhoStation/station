package controllers;

import com.so.tp.fx.Estacao;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class EstacoesController {

    @FXML
    private TableView<Estacao> tblEstacao;
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


    public void initialize() {
        //criar colunas da tabela
        colNumero = new TableColumn<>("Número");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLotacao = new TableColumn<>("Lotação");
        colLotacao.setCellValueFactory(new PropertyValueFactory<>("lotacao"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Estacao> selectionModel =
                tblEstacao.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        //adicionar colunas à tabela
        tblEstacao.getColumns().addAll(colNumero, colLotacao, colNome);

        //adicionar dados à tabela
        tblEstacao.setItems(FXCollections.observableArrayList(PainelControlo.verDadosEstacoes()));

    }
    public void onRowSelect(){
        Estacao estacao = tblEstacao.getSelectionModel().getSelectedItem();
        txtNumero.setText(String.valueOf(estacao.getNumero()));
        txtNome.setText(estacao.getNome());
        txtLotacao.setText(String.valueOf(estacao.getLotacao()));
    }
    public void onSaveClick() {
        //Criar estação
        if (txtNumero.getText().isEmpty() || txtNome.getText().isEmpty() || txtLotacao.getText().isEmpty()) {
            lblAlerta.setText("Preencha todos os campos!");
        } else {
            lblAlerta.setText("");
            //Estacao estacao = new Estacao(Integer.parseInt(txtNumero.getText()), Integer.parseInt(txtLotacao.getText()), txtNome.getText());
            //PainelControlo.guardarEstacoes(estacao);
            //Atualizar tabela
            tblEstacao.setItems(FXCollections.observableArrayList(PainelControlo.verDadosEstacoes()));
        }
    }

    //public void onUpdateClick(){
    //    PainelControlo.atualizarDadosTxt("estacoes.txt", txtNumero.getText(), txtNumero.getText() + ", " + txtNome.getText() + ", " +  txtLotacao.getText());
    //    tblEstacao.setItems(FXCollections.observableArrayList(PainelControlo.verDadosEstacoes()));
    //}
//
    //public void onDeleteClick() throws IOException {
    //    PainelControlo.apagarDadosTxt("estacoes.txt", txtNumero.getText());
    //    tblEstacao.setItems(FXCollections.observableArrayList(PainelControlo.verDadosEstacoes()));
    //}
}
