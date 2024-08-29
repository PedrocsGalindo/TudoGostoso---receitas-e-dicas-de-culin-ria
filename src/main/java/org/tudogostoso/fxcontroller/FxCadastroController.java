package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastroController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private TextField campoEmail;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoCadastro;

    private String tipoUsuario = "Usuário Padrão"; // Por padrão, o tipo de usuário é "Usuário Padrão"

    @FXML
    private void cadastrarUsuario() {
        String usuario = campoUsuario.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        mostrarAlerta(AlertType.INFORMATION, "Cadastro Realizado",
                "Usuário: " + usuario + "\nEmail: " + email + "\nSenha: " + senha + "\nTipo de Usuário: "
                        + tipoUsuario);
        limparCampos();
    }

    @FXML
    private void selecionarUsuarioPadrao() {
        tipoUsuario = "Usuário Padrão";
        mostrarAlerta(AlertType.INFORMATION, "Tipo de Usuário Selecionado", "Você selecionou: Usuário Padrão");
    }

    @FXML
    private void selecionarUsuarioChef() {
        tipoUsuario = "Usuário Chef";
        mostrarAlerta(AlertType.INFORMATION, "Tipo de Usuário Selecionado", "Você selecionou: Usuário Chef");
    }

    @FXML
    private void voltarParaLogin() {
        try {
            AppPrincipal.mudarTela("TelaLogin.fxml"); // Chama a troca para a tela de login
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(AlertType tipoAlerta, String titulo, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void limparCampos() {
        campoUsuario.clear();
        campoEmail.clear();
        campoSenha.clear();
    }
}
