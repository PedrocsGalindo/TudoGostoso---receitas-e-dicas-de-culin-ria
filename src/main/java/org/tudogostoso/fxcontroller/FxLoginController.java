package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.exceptions.UsuarioInexistenteException;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

import java.util.HashMap;
import java.util.Map;


public class FxLoginController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoSenha;

    private static final Controle controle = ControleFactory.criarControleGeral();

    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    // Armazenamento simulado de usuários e senhas
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        // Usuários e senhas pré-cadastrados
        usuarios.put("admin", "1234");
        usuarios.put("user", "abcd");
    }

    @FXML
    private void fazerLogin(ActionEvent event) {
        String cpf = campoUsuario.getText();
        String senha = campoSenha.getText();

        try {

            Usuario usuario = controle.recuperarUsuarioPorCpf(cpf)  ;

            // Verifica se a senha corresponde
            if (usuario != null && usuario.getSenha().equals(senha)) {
                mostrarAlerta(AlertType.INFORMATION, "Login Sucesso", "Bem-vindo, " + usuario.getNome() + "!");
                Sessao.setUsuarioSessao(usuario); // Define o usuário na sessão
                gerenciadorTelas.mudarTela("listaDeComprasV", event);
            } else {
                mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário ou senha incorretos.");
            }
        } catch (UsuarioInexistenteException e) {
            mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário não encontrado.");
        }

        limparCampos();
    }

    @FXML
    private void irParaTelaCadastro (ActionEvent event) {
       gerenciadorTelas.mudarTela("cadastro",event);
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

