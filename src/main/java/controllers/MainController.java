package controllers;

import com.so.tp.fx.Comboio;
import com.so.tp.fx.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TextField txt1;
    @FXML
    private Button btnEstacoes, btnComboios, btnLinhas, btnHorarios, btnLinhasEstacoes, btnLinhasHorarios, btnIniciar;

    public void initialize() {
        btnIniciar.requestFocus();
    }

    @FXML
    protected void onBtnIniciarClick() throws InterruptedException {
        if (Main.comboiosParaPartir.size()==0){
            System.out.println("Não existem comboios para partir, é necessário ir à gestão de comboios e selecionar os comboios que pretende iniciar.");
        } else {
            Main.iniciaComboios(Main.comboiosParaPartir);
        }
    }

    @FXML
    protected void onBtnEstacoesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("estacoes-view.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ESTAÇÕES");
        stage.setScene(new Scene(root1));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnComboiosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("comboios-view.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("COMBOIOS");
        stage.setScene(new Scene(root2));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnLinhasClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linhas-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("LINHAS");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnHorariosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("horarios-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("HORÁRIOS");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnGerirBilhetesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("bilhetes-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("BILHETES");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnPassageirosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("passageiros-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("PASSAGEIROS");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    protected void onBtnLinhasEstacoesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linhasEstacoes-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("LINHAS - ESTAÇÕES");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    @FXML
    protected void onBtnLinhasHorariosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("horariosLinhas-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("HORÁRIOS - LINHAS");
        stage.setScene(new Scene(root3));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
}