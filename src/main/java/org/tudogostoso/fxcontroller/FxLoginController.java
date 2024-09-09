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

import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

public class FxLoginController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoSenha;

    private static final Controle controle = ControleFactory.criarControleGeral();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    private void fazerLogin(ActionEvent event) {
        String identificador = campoUsuario.getText();  // Pra CPF ou email
        String senha = campoSenha.getText();

        try {
            Usuario usuario = null;

            //primeiro a busca é feito pelo cpf
            try {
                usuario = controle.recuperarUsuarioPorCpf(identificador);
            } catch (UsuarioInexistenteException e) {
                //A segunda verificaçaõ é pelo email
                try {
                    InternetAddress email = new InternetAddress(identificador);
                    usuario = controle.recuperarUsuarioPorEmail(email);
                } catch (AddressException emailEx) {
                ;
                }
            }

            if (usuario != null && usuario.getSenha().equals(senha)) {
                mostrarAlerta(AlertType.INFORMATION, "Login Sucesso", "Bem-vindo, " + usuario.getNome() + "!");
                Sessao.setUsuarioSessao(usuario);
                gerenciadorTelas.mudarTela("feed", event);
            } else {
                mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário ou senha incorretos.");
            }
        } catch (UsuarioInexistenteException e) {
            mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário não encontrado.");
        }

        limparCampos();
    }

    @FXML
    private void irParaTelaCadastro(ActionEvent event) {
        gerenciadorTelas.mudarTela("cadastro", event);
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
