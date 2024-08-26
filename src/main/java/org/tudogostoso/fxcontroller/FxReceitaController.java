package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.tudogostoso.modelo.Sessao;


import java.io.File;
import java.io.IOException;

public class FxReceitaController {

    @FXML
    private ImageView imagemReceita;

    @FXML
    private Button botaoVoltar;

    @FXML
    public void initialize() {
        imagemReceita.setImage(new Image(new File(Sessao.getReceitaSessao().getCaminhoImagem()).getAbsolutePath()));
    }
    @FXML
    void botaoVoltar(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(Sessao.getUltimaCena()));
        Scene scene = new Scene(root);

        // Obtenha a Stage a partir do evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}
