package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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

    private static final Controle controle = ControleFactory.criarControleGeral();
    private Usuario usuario;

    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    private void cadastrarUsuario(ActionEvent event){
        String nomeUsuario = campoUsuario.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        String cpf = campoCpf.getText();

        //validações, vai criar o usuario
        try{
            usuario = controle.criarUsuario(nomeUsuario, senha, email, cpf);

            //caso for UsuarioChef
            if (checkBoxChef.isSelected()) {
                try {
                    Sessao.setUsuarioSessao(controle.criarUsuarioChef(usuario));
                    limparCampos();
                    tipoUsuario  = "Usuário Chef";
                    mostrarAlerta(AlertType.INFORMATION, "Cadastro Realizado",
                            "UsuárioChef: " + usuario + "\nEmail: " + email + "\nSenha: " + senha + "\nTipo de Usuário: "
                                    + tipoUsuario);
                    gerenciadorTelas.mudarTela("feed", event);
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
                gerenciadorTelas.mudarTela("feed", event);
            }
        } catch (NullPointerException e){
            mostrarAlerta(AlertType.ERROR, "Campo vazio",
                    "Um dos campos esta vazio, por favor preencha todos com suas informações");
        } catch (AddressException e){
            mostrarAlerta(AlertType.ERROR, "Email invalido",
                    "Insira um email valido");
        } catch(UsuarioJaExistenteException e){
            mostrarAlerta(AlertType.ERROR, "Usuario ja existente",
                    e.getMessage());
        }
    }

    @FXML
    private void voltarParaLogin(ActionEvent event) {
        gerenciadorTelas.mudarTela("Login",event);
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
