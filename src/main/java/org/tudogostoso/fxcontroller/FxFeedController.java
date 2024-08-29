package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;

import java.io.IOException;
import java.util.List;

public class FxFeedController {

    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelReceitas;

    @FXML
    private VBox feed;

    @FXML
    private Button btnPerfil;

    private Controle controle;

    @FXML
    public void initialize() {
        controle = ControleFactory.criarControleGeral();
        List<Receita> receitas = controle.buscarReceitasAleatorias();

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
                mostrarMaisInformacoes(receita);
            });

            detalhesReceita.getChildren().addAll(titulo, autor, tempoPreparo, categoria);
            receitaBox.getChildren().addAll(detalhesReceita, btnVerMais);

            feed.getChildren().add(receitaBox);
        }
    }
    private void abrirPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tudogostoso/telas/perfil.fxml"));
            VBox perfilLayout = loader.load();
            Scene scene = new Scene(perfilLayout);
            Stage perfilStage = new Stage();
            perfilStage.setScene(scene);
            perfilStage.setTitle("Meu Perfil");
            perfilStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMaisInformacoes(Receita receita) {
        // Implementar funcionalidade para exibir mais informações sobre a receita
        System.out.println("Exibindo mais informações sobre a receita: " + receita.getTitulo());
    }
}
