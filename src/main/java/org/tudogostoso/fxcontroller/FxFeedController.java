package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;

import java.io.File;
import java.util.List;

public class FxFeedController {

    @FXML
    private ListView<Receita> feedList; // Alterado para ListView<Receita> para permitir o uso de ReceitaListCell


    @FXML
    void handleVerMais(ActionEvent event) {
        Receita selectedReceita = feedList.getSelectionModel().getSelectedItem();
        if (selectedReceita != null) {
            mostrarMaisInformacoes(event, selectedReceita);
        }
    }

    @FXML
    private GridPane gridBTRPerfil, gridBTNBuscarReceitas;

    private final Controle controle = ControleFactory.criarControleGeral();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        List<Receita> receitas = controle.buscarReceitasAleatorias();


        feedList.setItems(FXCollections.observableArrayList(receitas));
        feedList.setCellFactory(param -> new FeedReceitaListCell());
    }

    private void mostrarMaisInformacoes(ActionEvent event, Receita receita) {
        Sessao.setReceitaSessao(receita);
        Sessao.setUltimaCena("feed");
        gerenciadorTelas.mudarTela("receita", event);
    }

    @FXML
    void meuPerfil(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }

    @FXML
    void buscarReceitas(ActionEvent event) {
        gerenciadorTelas.mudarTela("buscar", event);
    }


    @FXML
    void mousePorCimaBotaoBusca() {
        gridBTNBuscarReceitas.setStyle("-fx-background-color: #5aa55a;");
    }

    @FXML
    void mousePorForaBotaoBusca() {
        gridBTNBuscarReceitas.setStyle("-fx-background-color: #90ee90; -fx-border-color: #5aa55a; -fx-border-width: 3px;");
    }

    @FXML
    void mousePorCimaBotaoPerfil() {
        gridBTRPerfil.setStyle("-fx-background-color: #5aa55a;");
    }

    @FXML
    void mousePorForaBotaoPerfil() {
        gridBTRPerfil.setStyle("-fx-background-color: #90ee90; -fx-border-color: #5aa55a; -fx-border-width: 3px;");
    }

    // Célula personalizada interna
    private static class FeedReceitaListCell extends ListCell<Receita> {
        private final HBox content;
        private final ImageView imageView;
        private final Label titulo;
        private final Label categoria;
        private final Label notaMedia;


        public FeedReceitaListCell() {
            super();
            imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            titulo = new Label();
            titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            categoria = new Label();
            categoria.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");

            notaMedia = new Label();
            notaMedia.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");


            VBox vBox = new VBox(titulo, categoria, notaMedia);
            vBox.setSpacing(5);

            content = new HBox(imageView, vBox);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Receita item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                titulo.setText(item.getTitulo());
                categoria.setText(item.getCategoria());
                notaMedia.setText("Nota: " + String.format("%.1f", (double) item.getNota()));


                File file = new File(item.getCaminhoImagem());
                Image image;
                if (file.exists()) {
                    image = new Image(file.toURI().toString(), 50, 50, false, true);
                } else {

                    image = new Image(getClass().getResourceAsStream("/org/tudogostoso/Imagens/fotoDefaultReceitas.jpg"), 50, 50, false, true);
                }
                imageView.setImage(image);
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }
}
