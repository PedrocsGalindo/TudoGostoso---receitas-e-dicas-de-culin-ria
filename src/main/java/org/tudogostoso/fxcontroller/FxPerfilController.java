package org.tudogostoso.fxcontroller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.tudogostoso.controle.ControleReceita;
import org.tudogostoso.controle.ControleUsuario;
import org.tudogostoso.modelo.Usuario;
import org.tudogostoso.repositorios.RepositorioReceitas;
import org.tudogostoso.repositorios.RepositorioUsuarios;

public class FxPerfilController {

    @FXML
    private Button BTNListaDeCompras;

    @FXML
    private Button BTNMinhasReceitasFavoritas;

    @FXML
    private Button BTNVoltar;

    @FXML
    private Label LabelCPF;

    @FXML
    private Label LabelD;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelNomeDeUsuario;

    private Usuario usuario;
    private ControleUsuario controleUsuario;



    public void setControleUsuario(ControleUsuario controleUsuario) {
        this.controleUsuario = controleUsuario;
    }

    @FXML
    public void initialize() {

        LabelNomeDeUsuario.setText(usuario.getNome());
        LabelD.setText(String.valueOf(usuario.getId()));
        LabelCPF.setText(usuario.getCpf());
        LabelEmail.setText(String.valueOf(usuario.getEmail()));
    }



}
