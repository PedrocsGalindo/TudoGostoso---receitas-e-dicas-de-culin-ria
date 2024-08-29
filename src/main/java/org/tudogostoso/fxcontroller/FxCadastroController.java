package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;

public class FxCadastroController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private TextField campoEmail;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoCadastro;

    private String tipoUsuario = "Usuário Padrão"; // Por padrão, o tipo de usuário é "Usuário Padrão"

    private static Controle controle = ControleFactory.criarControleGeral();
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private void voltarParaLogin(ActionEvent event) {
        try {
            mudarTela("/org/tudogostoso/telas/login.fxml", event); // Chama a troca para a tela de login
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void mudarTela(String tela, Event evento) {
        try {root = FXMLLoader.load(getClass().getResource(tela));
            scene = new Scene(root);

            // Obtenha a Stage a partir do evento
            stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();;  // Chama a troca para a tela de cadastro ou feed
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
