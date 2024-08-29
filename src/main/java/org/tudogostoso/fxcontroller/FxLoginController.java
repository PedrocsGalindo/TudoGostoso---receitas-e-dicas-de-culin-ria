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
import org.tudogostoso.modelo.Sessao;

public class FxLoginController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoLogin;
    @FXML
    private Button botaoIrParaCadastro;

    private static Controle controle = ControleFactory.criarControleGeral();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void fazerLogin(ActionEvent event)  {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        // Simulação de verificação de login
        if ("admin".equals(usuario) && "1234".equals(senha)) {
            mostrarAlerta(AlertType.INFORMATION, "Login Sucesso", "Bem-vindo, " + usuario + "!");
            //Sessao.setUsuarioSessao(usuario); tem que fazer a verificação e intanciar o objeto para essa linha funcionar
            mudarTela("/org/tudogostoso/telas/feed.fxml", event);
        } else {
            mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário ou senha incorretos.");
        }

        limparCampos();
    }
    @FXML
    void irParaTelaCadastro(ActionEvent event) {
        mudarTela("/org/tudogostoso/telas/cadastro.fxml", event);

    }

    private void mudarTela(String tela, Event evento) {
        try {
            root = FXMLLoader.load(getClass().getResource(tela));
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
        campoSenha.clear();
    }
}
