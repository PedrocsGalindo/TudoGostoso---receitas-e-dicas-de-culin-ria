package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.tudogostoso.modelo.Receita;

import java.io.File;
import java.util.List;

public class FxReceitasFavoritasController {

    @FXML
    private ListView<Receita> listaFavoritos;

    private ObservableList<Receita> favoritosList;

    private ControleReceita controleReceita;

    @FXML
    public void initialize() {
        favoritosList = FXCollections.observableArrayList();
        listaFavoritos.setItems(favoritosList);
        listaFavoritos.setCellFactory(new Callback<ListView<Receita>, ListCell<Receita>>() {
            @Override
            public ListCell<Receita> call(ListView<Receita> param) {
                return new ReceitaListCell();
            }
        });
        carregarFavoritos();
    }

    private void carregarFavoritos() {
        List<Receita> receitasFavoritas = controleReceita.buscarReceitasFavoritas(); // Método que busca receitas favoritas
        favoritosList.setAll(receitasFavoritas);
    }

    private void verReceita(Receita receita) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes da Receita");
        alert.setHeaderText(receita.getTitulo());
        alert.setContentText(receita.toString());
        alert.showAndWait();
    }

    private void removerFavorito(Receita receita) {
        controleReceita.removerReceitaFavorita(receita); // Método para remover receita dos favoritos
        carregarFavoritos();
    }

    public void setControleReceita(ControleReceita controleReceita) {
        this.controleReceita = controleReceita;
        carregarFavoritos();
    }

    public class ReceitaListCell extends ListCell<Receita> {
        private HBox content;
        private ImageView imageView;
        private VBox vBox;
        private Label titulo;
        private Label categoria;
        private Label notaMedia;
        private Button verButton;
        private Button removerButton;

        public ReceitaListCell() {
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

            verButton = new Button("Ver Receita");
            verButton.getStyleClass().add("button");
            verButton.setOnAction(e -> verReceita(getItem()));

            removerButton = new Button("Remover");
            removerButton.getStyleClass().add("button");
            removerButton.getStyleClass().add("remove");
            removerButton.setOnAction(e -> removerFavorito(getItem()));

            HBox buttonBox = new HBox(verButton, removerButton);
            buttonBox.setSpacing(10);
            buttonBox.setAlignment(Pos.CENTER_RIGHT);

            vBox = new VBox(titulo, categoria, notaMedia);
            vBox.setSpacing(5);
            content = new HBox(imageView, vBox, buttonBox);
            content.setSpacing(10);
            content.setAlignment(Pos.CENTER_LEFT);
        }

        @Override
        protected void updateItem(Receita item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                titulo.setText(item.getTitulo());
                categoria.setText(item.getCategoria());
                notaMedia.setText("Nota: " + String.format("%.1f", item.getNota()));

                // Carregar a imagem da receita
                File file = new File(item.getCaminhoImagem());
                Image image;
                if (file.exists()) {
                    image = new Image(file.toURI().toString(), 50, 50, false, true);
                } else {
                    // Imagem padrão se não encontrar
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
