package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.tudogostoso.modelo.Sessao;

import java.io.File;

public class FxReceitaController {

    @FXML
    private ImageView imagemReceita;

    @FXML
    private Button botaoVoltar;

    @FXML
    public void initialize() {
        imagemReceita.setImage(new Image(new File(Sessao.getReceitaSessao().getCaminhoImagem()).getAbsolutePath()));
    }

}
