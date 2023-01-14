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
    private Button btnEstacoes, btnComboios, btnLinhas, btnHorarios, btnLinhasEstacoes, btnLinhasHorarios;

    @FXML
    protected void onBtnIniciarClick(){
        Main.iniciaComboios(Main.comboiosParaPartir);
//        for (int i =0;i<Main.comboiosParaPartir.size();i++){
//            Comboio comboio = Main.comboiosParaPartir.get(i);
//            comboio.start();
//        }
    }

    @FXML
    protected void onBtnEstacoesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("estacoes-view.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Estações");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    protected void onBtnComboiosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("comboios-view.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Comboios");
        stage.setScene(new Scene(root2));
        stage.show();
    }

    @FXML
    protected void onBtnLinhasClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linhas-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Linhas");
        stage.setScene(new Scene(root3));
        stage.show();
    }

    @FXML
    protected void onBtnHorariosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("horarios-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Linhas");
        stage.setScene(new Scene(root3));
        stage.show();
    }

    @FXML
    protected void onBtnGerirBilhetesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("bilhetes-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Linhas");
        stage.setScene(new Scene(root3));
        stage.show();
    }

    @FXML
    protected void onBtnPassageirosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("passageiros-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Linhas");
        stage.setScene(new Scene(root3));
        stage.show();
    }

    @FXML
    protected void onBtnLinhasEstacoesClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linhasEstacoes-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Linhas - Estações");
        stage.setScene(new Scene(root3));
        stage.show();
    }
    @FXML
    protected void onBtnLinhasHorariosClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("horariosLinhas-view.fxml"));
        Parent root3 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Horários - Linhas");
        stage.setScene(new Scene(root3));
        stage.show();
    }
}