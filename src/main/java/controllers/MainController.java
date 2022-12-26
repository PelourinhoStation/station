package controllers;

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
    private Button btnEstacoes, btnComboios, btnLinhas, btnHorarios;

    @FXML
    protected void onBtnSaveClick() {
        System.out.println("Hello, " + txt1.getText() + "!");
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
}