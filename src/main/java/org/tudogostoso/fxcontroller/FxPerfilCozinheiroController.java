package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.tudogostoso.modelo.Sessao;
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

    @FXML
    public void initialize() {

        UsuarioChef usuarioChef = Sessao.getUsuarioSessao();

        LabelD.setText(String.valueOf(usuarioChef.getId()));
        LabelNomeDeUsuario.setText(usuarioChef.getNome());
        LabelCPF.setText(usuarioChef.getCpf());
        LabelEmail.setText(String.valueOf(usuarioChef.getEmail()));

    }


}
