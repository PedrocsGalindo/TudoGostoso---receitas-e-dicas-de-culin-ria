package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;
import org.tudogostoso.modelo.UsuarioChef;

public class FxPerfilCozinheiroController {

    @FXML
    private Button BTNListaDeCompras;

    @FXML
    private Button BTNMinhaReceitasFavoritas;

    @FXML
    private Button BTNMinhasReceitas;

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

    private Controle controle = ControleFactory.criarControleGeral();
    private UsuarioChef joaquim = (UsuarioChef) controle.recuperarUsuarioPorId(3);
    @FXML
    public void initialize() {
        Sessao.setUsuarioSessao(joaquim);
        Usuario usuario;
        if (Sessao.getUsuarioSessao() instanceof UsuarioChef) {
            usuario = (UsuarioChef) Sessao.getUsuarioSessao();
        }else{
            usuario = Sessao.getUsuarioSessao();
        }
        LabelD.setText(String.valueOf(usuario.getId()));
        LabelNomeDeUsuario.setText(usuario.getNome());
        LabelCPF.setText(usuario.getCpf());
        LabelEmail.setText(String.valueOf(usuario.getEmail()));

    }


}
