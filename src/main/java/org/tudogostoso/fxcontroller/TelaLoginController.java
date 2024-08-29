import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaLoginController {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoLogin;
    @FXML
    private Button botaoIrParaCadastro;

    @FXML
    private void fazerLogin() {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        // Simulação de verificação de login
        if ("admin".equals(usuario) && "1234".equals(senha)) {
            mostrarAlerta(AlertType.INFORMATION, "Login Sucesso", "Bem-vindo, " + usuario + "!");
        } else {
            mostrarAlerta(AlertType.ERROR, "Falha no Login", "Usuário ou senha incorretos.");
        }

        limparCampos();
    }

    @FXML
    private void irParaTelaCadastro() {
        try {
            AppPrincipal.mudarTela("TelaCadastro.fxml");  // Chama a troca para a tela de cadastro
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
