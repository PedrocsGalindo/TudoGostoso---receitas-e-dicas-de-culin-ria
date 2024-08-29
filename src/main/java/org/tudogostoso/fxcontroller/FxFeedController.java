package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class FxFeedController {
    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelReceitas;

    @FXML
    private GridPane feed;

    @FXML
    private Button btnPerfil;
    private Controle controle;

    @FXML
    public void initialize() {
        controle = ControleFactory.criarControleGeral();
        List<Receita> receitas = controle.buscarReceitasAleatorias();

        int row = 0;


        for (Receita receita : receitas) {
            HBox receitaBox = new HBox();
            receitaBox.setSpacing(10);

            VBox detalhesReceita = new VBox();
            detalhesReceita.setSpacing(5);

            Label titulo = new Label("Título: " + receita.getTitulo());
            Label autor = new Label("Autor: " + receita.getAutor().getNome());

            Label tempoPreparo = new Label("Tempo de Preparo: " + receita.getTempoDePreparo());
            Label categoria = new Label("Categoria: " + receita.getCategoria());

            Button btnVerMais = new Button("Ver Mais");
            btnVerMais.setOnAction(event -> {

            });

            detalhesReceita.getChildren().addAll(titulo, autor, tempoPreparo, categoria);
            receitaBox.getChildren().add(detalhesReceita);


            feed.add(receitaBox, 1, row);
            feed.add(btnVerMais, 2, row);
            row++;
        }
    }
}
