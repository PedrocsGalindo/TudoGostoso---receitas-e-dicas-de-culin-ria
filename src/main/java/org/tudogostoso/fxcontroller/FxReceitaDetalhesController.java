package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.tudogostoso.modelo.Receita;

import java.io.File;

public class FxReceitaDetalhesController {

    @FXML
    private Label tituloLabel;

    @FXML
    private Label autorLabel;

    @FXML
    private Label tempoPreparoLabel;

    @FXML
    private Label categoriaLabel;

    @FXML
    private Label ingredientesLabel;

    @FXML
    private Label preparoLabel;



    public void setReceita(Receita receita) {
        tituloLabel.setText(receita.getTitulo());
        autorLabel.setText(receita.getAutor().getNome());
        tempoPreparoLabel.setText(receita.getTempoDePreparo());
        categoriaLabel.setText(receita.getCategoria());

        StringBuilder ingredientesText = new StringBuilder();
        receita.getIngredientes().forEach(item -> ingredientesText.append(item.toString()).append("\n"));
        ingredientesLabel.setText(ingredientesText.toString());

        StringBuilder preparoText = new StringBuilder();
        receita.getPreparo().forEach(step -> preparoText.append(step).append("\n"));
        preparoLabel.setText(preparoText.toString());

        }
    }

