package com.example.chat;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChatController {

    @FXML
    private TextField UsuarioTextField;

    @FXML
    private Button enviarButton;

    @FXML
    private TextField mensagelTextField;

    @FXML
    private ListView<String> mensagensList;

    @FXML
    private Label pessoaChatLabel;

    @FXML
    private Button salvarUsuarioButton;

    private Chat chat;
    private User usuario = new User();

    public void initialize() {
        try {
            chat = new Chat();
            chat.receberMensagens(mensagensList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarUsuario() {
        this.usuario.setNome(UsuarioTextField.getText());
        if (usuario.usuarioIdentificado()) {
            this.enviarButton.setDisable(false);
            this.UsuarioTextField.setDisable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Você precisa informar um nome de usuario!");
            alert.showAndWait();
        }
    }

    public void enviarMensagem() {
        if (!this.mensagelTextField.getText().isEmpty() && !this.mensagelTextField.getText().isBlank()) {
            chat.enviarMensagem(new Mensagem(this.usuario, this.mensagelTextField.getText()));
            this.mensagelTextField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Você precisa digitar a Mensagem!");
            alert.showAndWait();
        }
    }
}
