package controllers;

import com.so.tp.fx.Horario;
import com.so.tp.fx.PainelControlo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class HorariosController {
    @FXML
    private TableView<Horario> tblHorarios;
    private TableColumn<Horario, Integer> colNumero;
    private TableColumn<Horario, Integer> colIdComboio;
    private TableColumn<Horario, Integer> colPartida;
    private TableColumn<Horario, String> colChegada;
    private TableColumn<Horario, String> colSentido;

    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtPartida;
    @FXML
    private TextField txtChegada;
    @FXML
    private ComboBox cbSentido;
    @FXML
    private ComboBox cbComboio;
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
        colPartida = new TableColumn<>("Hora de partida");
        colPartida.setCellValueFactory(new PropertyValueFactory<>("horaPartida"));
        colChegada = new TableColumn<>("Hora de chegada");
        colChegada.setCellValueFactory(new PropertyValueFactory<>("horaChegada"));
        colSentido = new TableColumn<>("Sentido");
        colSentido.setCellValueFactory(new PropertyValueFactory<>("sentido"));
        colIdComboio = new TableColumn<>("Comboio");
        colIdComboio.setCellValueFactory(new PropertyValueFactory<>("idComboio"));

        //Permitir a seleção de apenas uma linha da tabela
        TableView.TableViewSelectionModel<Horario> selectionModel =
                tblHorarios.getSelectionModel();
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

        tblHorarios.getColumns().addAll(colNumero, colPartida, colChegada, colSentido, colIdComboio);

        initializeComboBox();
        getDataToTableView();

    }

    public void getDataToTableView() throws IOException, ClassNotFoundException {
        PainelControlo.horarios.clear();
        PainelControlo.getDados();
        //adicionar dados à tabela
        tblHorarios.setItems(FXCollections.observableArrayList(PainelControlo.horarios));
    }

    public void initializeComboBox() {
        cbSentido.getItems().clear();
        cbComboio.getItems().clear();

        cbSentido.getItems().addAll("Ida", "Volta");
        cbSentido.getSelectionModel().selectFirst();

        for (int i = 0; i < PainelControlo.comboios.size(); i++) {
            cbComboio.getItems().add(PainelControlo.comboios.get(i).getNumero());
        }
        cbComboio.getSelectionModel().selectFirst();
    }

    public void onRowSelect(){
        btnSave.setDisable(true);
        btnAtualizar.setDisable(false);
        btnEliminar.setDisable(false);
        btnNovo.setDisable(false);

        if (tblHorarios.getSelectionModel().getSelectedItem() != null) {
            // Obtenha o item selecionado
            Horario selectedItem = tblHorarios.getSelectionModel().getSelectedItem();
            // Atualize os campos de entrada com os valores do 'item' selecionado
            txtNumero.setText(String.valueOf(selectedItem.getNumero()));
            txtChegada.setText(selectedItem.getHoraChegada());
            txtPartida.setText(selectedItem.getHoraPartida());
            cbSentido.setValue(selectedItem.getSentido());
            cbComboio.setValue(selectedItem.getIdComboio());
        }
    }

    public void onNovoClick(){
        btnSave.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNovo.setDisable(true);

        txtNumero.setText("");
        txtChegada.setText("");
        txtPartida.setText("");
    }

    public void onSaveClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.insertHorarios(txtPartida.getText(), txtChegada.getText(), cbSentido.getValue().toString(), Integer.parseInt(cbComboio.getValue().toString()));
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao guardar os dados!");
        }
    }

    public void onUpdateClick() throws IOException, ClassNotFoundException {
        try {
            PainelControlo.updateHorarios(Integer.parseInt(txtNumero.getText()), txtPartida.getText(), txtChegada.getText(), cbSentido.getValue().toString(), Integer.parseInt(cbComboio.getValue().toString()));
            getDataToTableView();
        } catch (Exception e){
            lblAlerta.setText("Erro ao atualizar os dados!");
        }
    }

    public void onDeleteClick() throws IOException {
        try {
            PainelControlo.deleteData(Integer.parseInt(txtNumero.getText()), "horarios");
            getDataToTableView();
        } catch (Exception e) {
            lblAlerta.setText("Erro ao eliminar os dados!");
        }
    }
}
