package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorsController {

    private String mensagem;

    public ErrorsController(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @FXML
    private Label lbErro;

    public void initialize() {
        System.out.println("Mensagem: " + mensagem);
        lbErro.setText(getMensagem());
    }
}
