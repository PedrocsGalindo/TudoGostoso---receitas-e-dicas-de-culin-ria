package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

import java.io.File;

public class FxReceitasFavoritasController {

    @FXML
    private ListView<Receita> listaFavoritos;

    private ObservableList<Receita> favoritosList;
    private final Usuario usuario = Sessao.getUsuarioSessao();
    private final Controle controle = ControleFactory.criarControleGeral();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        controle.verificacaoListaFav(usuario);
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
        favoritosList.setAll(usuario.getReceitasFav());
    }

    @FXML
    private void verReceita(ActionEvent event, Receita receita) {
        Sessao.setReceitaSessao(receita);
        Sessao.setUltimaCena("receitasFavoritas");
        gerenciadorTelas.mudarTela("receita", event); // Tela para ver receita
    }

    @FXML
    private void removerFavorito(Receita receita) {
        controle.removerReceitaFavorita(usuario, receita);
        carregarFavoritos();
    }

    @FXML
    private void voltarParaFeed(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }

    // Métodos adicionados para correspondência com o FXML
    @FXML
    private void handleBotaoVerReceita(ActionEvent event) {
        // Aqui você pode pegar a receita selecionada e chamar verReceita com ela
        Receita receitaSelecionada = listaFavoritos.getSelectionModel().getSelectedItem();
        if (receitaSelecionada != null) {
            verReceita(event, receitaSelecionada);
        }
    }

    @FXML
    private void handleBotaoExcluirFavoritos(ActionEvent event) {
        // Aqui você pode pegar a receita selecionada e chamar removerFavorito com ela
        Receita receitaSelecionada = listaFavoritos.getSelectionModel().getSelectedItem();
        if (receitaSelecionada != null) {
            removerFavorito(receitaSelecionada);
        }
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
            verButton.setOnAction(e -> verReceita(e, getItem()));

            removerButton = new Button("Remover");
            removerButton.getStyleClass().add("button");
            removerButton.getStyleClass().add("remove");
            removerButton.setOnAction(e -> removerFavorito(getItem()));

            HBox buttonBox = new HBox(verButton, removerButton);
            buttonBox.setSpacing(10);

            vBox = new VBox(titulo, categoria, notaMedia);
            vBox.setSpacing(5);
            content = new HBox(imageView, vBox, buttonBox);
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
