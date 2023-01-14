package controllers;

import com.so.tp.fx.Estacao;
import com.so.tp.fx.Linha;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class linhasEstacoesController {
    @FXML
    private TableView<Estacao.LinhasEstacoes> tblLinhasEstacoes;
    private TableColumn<Estacao.LinhasEstacoes, Integer> colNumero;
    private TableColumn<Estacao.LinhasEstacoes, Integer> colLinha;
    private TableColumn<Estacao.LinhasEstacoes, String> colNome;

    @FXML
    private ComboBox cbLinha;
    @FXML
    private ComboBox cbEstacao;

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
        colNome = new TableColumn<>("Nome Estação");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLinha = new TableColumn<>("Linha");
        colLinha.setCellValueFactory(new PropertyValueFactory<>("idLinha"));


        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Estacao.LinhasEstacoes> selectionModel =
                tblLinhasEstacoes.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblLinhasEstacoes.getColumns().addAll(colNumero, colNome, colLinha);

        getDataToTableView();
    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.linhasEstacoes.clear();
        PainelControlo.getDados();
        //adicionar os dados à combobox
        initializeComboBox();
        //adicionar dados à tabela
        tblLinhasEstacoes.setItems(FXCollections.observableArrayList(PainelControlo.linhasEstacoes));
    }

    public void initializeComboBox() {
        cbLinha.getItems().clear();
        cbEstacao.getItems().clear();

        for (int i = 0; i < PainelControlo.linhas.size(); i++) {
            cbLinha.getItems().add(PainelControlo.linhas.get(i).getNumero() + " - " + PainelControlo.linhas.get(i).getNome());
        }

        for (int i = 0; i < PainelControlo.estacoes.size(); i++) {
            cbEstacao.getItems().add(PainelControlo.estacoes.get(i).getNumero() + " - " + PainelControlo.estacoes.get(i).getNome());
        }
    }

    public void onRowSelect() {
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblLinhasEstacoes.getSelectionModel().getSelectedItem() != null) {
            Estacao.LinhasEstacoes linhaEstacao = tblLinhasEstacoes.getSelectionModel().getSelectedItem();
            cbLinha.getSelectionModel().select(linhaEstacao.getIdLinha() - 1);
            cbEstacao.getSelectionModel().select(linhaEstacao.getNumeroEstacao() - 1);
            nRegisto = linhaEstacao.getNumero();
        }
    }

    public void onNovoClick() {
        cbLinha.getSelectionModel().clearSelection();
        cbEstacao.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);
        cbEstacao.requestFocus();
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {

        try {
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);
            System.out.println(idLinha);

            String estacao = cbEstacao.getValue().toString();
            int idEstacao = Integer.parseInt(estacao.split(" - ")[0]);
            System.out.println(idEstacao);

            PainelControlo.insertLinhasEstacoes(idLinha, idEstacao);

            getDataToTableView();
        } catch (Exception e) {
            System.out.println("Erro ao guardar");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            String linha = cbLinha.getValue().toString();
            int idLinha = Integer.parseInt(linha.split(" - ")[0]);

            String estacao = cbEstacao.getValue().toString();
            int idEstacao = Integer.parseInt(estacao.split(" - ")[0]);

            PainelControlo.updateLinhasEstacoes(nRegisto, idLinha, idEstacao);

            getDataToTableView();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(nRegisto, "linhas_estacoes");
            getDataToTableView();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar");
        }
    }
}
