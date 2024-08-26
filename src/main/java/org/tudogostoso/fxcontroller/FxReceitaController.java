package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;


import java.io.File;
import java.io.IOException;

public class FxReceitaController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextArea textAreaAvaliações, textAreaIngrediente, textAreaTitulo, textAreaNota, textAreaPreparo;

    @FXML
    private ImageView imagemReceita;

    @FXML
    private ChoiceBox<?> choiceBoxNota;

    @FXML
    private TextField textFiledComentario;

    @FXML
    public void initialize() {
        Receita receita = Sessao.getReceitaSessao();
        imagemReceita.setImage(new Image(new File(receita.getCaminhoImagem()).getAbsolutePath()));
        textAreaTitulo.setText(receita.getTitulo());

        String nota = String.valueOf(receita.getNota());
        textAreaNota.setText(nota);

        String preparo = String.join(", " , receita.getPreparo());
        textAreaPreparo.setText(preparo);

    }

    @FXML
    void handllerBotaoAddListaCompra(ActionEvent event) {

    }

    @FXML
    void handllerBotaoAvaliar(ActionEvent event) {

    }

    @FXML
    void handllerBotaoVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Sessao.getUltimaCena()));
        Scene scene = new Scene(root);

        // Obtenha a Stage a partir do evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}

