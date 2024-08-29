package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.exceptions.UsuarioJaExistenteException;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

import javax.mail.internet.AddressException;

public class FxCadastroController {

    @FXML
    private TextField campoUsuario, campoEmail, campoCpf;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private CheckBox checkBoxChef;
    @FXML
    private Button botaoCadastro;

    private String tipoUsuario = "Usuário Padrão"; // Por padrão, o tipo de usuário é "Usuário Padrão"

    private static Controle controle = ControleFactory.criarControleGeral();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Usuario usuario;

    @FXML
    private void cadastrarUsuario(ActionEvent event) {
        String nomeUsuario = campoUsuario.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        String cpf = campoCpf.getText();

        //validações, vai criar o usuario
        try{
            usuario = controle.criarUsuario(nomeUsuario, senha, email, cpf);

        } catch (AddressException e){
            //alerta de email invalido
        } catch (NullPointerException e){
            //um dos campos esta vazio
        } catch(UsuarioJaExistenteException e){
         //ja existe usario com determinado elemento, use o e.getMessage para exebir ao usuario oque ja existe
        }

        //cria usuarioChef e linka a sessao
        if (checkBoxChef.isSelected()) {
            try {
                mostrarAlerta(AlertType.INFORMATION, "Cadastro Realizado",
                        "UsuárioChef: " + usuario + "\nEmail: " + email + "\nSenha: " + senha + "\nTipo de Usuário: "
                                + tipoUsuario);
                Sessao.setUsuarioSessao(controle.criarUsuarioChef(usuario));
                limparCampos();
                mudarTela("/org/tudogostoso/telas/feed.fxml", event);
            } catch (NullPointerException e){
                //mensagem de erro, esse erro acontece pq o usuario que usou para criar o Usuairio Chef é vazio
            }

        //linka o Usuario comun a sessao
        } else {
            mostrarAlerta(AlertType.INFORMATION, "Cadastro Realizado",
                    "Usuário: " + usuario + "\nEmail: " + email + "\nSenha: " + senha + "\nTipo de Usuário: "
                            + tipoUsuario);
            Sessao.setUsuarioSessao(usuario);
            limparCampos();
            mudarTela("/org/tudogostoso/telas/feed.fxml", event);
        }
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
