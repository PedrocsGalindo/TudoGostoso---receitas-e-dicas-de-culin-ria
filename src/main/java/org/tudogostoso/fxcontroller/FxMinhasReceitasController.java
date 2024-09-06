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
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.UsuarioChef;

import java.io.File;

public class FxMinhasReceitasController {

    @FXML
    private ListView<Receita> listViewMinhasReceitas;

    private ObservableList<Receita> minhasReceitasList;
    private final Controle controle = ControleFactory.criarControleGeral();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();
    private final UsuarioChef usuario = (UsuarioChef) Sessao.getUsuarioSessao();

    @FXML
    public void initialize() {
        minhasReceitasList = FXCollections.observableArrayList();
        listViewMinhasReceitas.setItems(minhasReceitasList);
        listViewMinhasReceitas.setCellFactory(new Callback<ListView<Receita>, ListCell<Receita>>() {
            @Override
            public ListCell<Receita> call(ListView<Receita> param) {
                return new ReceitaListCell();
            }
        });
        carregarMinhasReceitas();
    }

    public class ReceitaListCell extends ListCell<Receita> {
        private HBox hBoxReceita;
        private VBox vBoxReceita;
        private ImageView imageViewReceita;
        private Text textReceitaTitulo, textReceitaCategoriaENota;

        public ReceitaListCell() {
            super();
            imageViewReceita = new ImageView();
            imageViewReceita.setFitHeight(50);
            imageViewReceita.setFitWidth(50);
            textReceitaTitulo = new Text();
            textReceitaTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            textReceitaCategoriaENota = new Text();
            textReceitaCategoriaENota.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");

            vBoxReceita = new VBox(textReceitaTitulo, textReceitaCategoriaENota);
            vBoxReceita.setSpacing(5);
            hBoxReceita = new HBox(imageViewReceita, vBoxReceita);
            hBoxReceita.setSpacing(10);
        }

        @Override
        protected void updateItem(Receita item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                textReceitaTitulo.setText(item.getTitulo());
                textReceitaCategoriaENota.setText("Categoria: " + item.getCategoria() + "\n" + "Nota: " + String.format("%.1f", (double) item.getNota()));

                File file = new File(item.getCaminhoImagem());
                Image image;
                if (file.exists()) {
                    image = new Image(file.toURI().toString(), 50, 50, false, true);
                } else {
                    image = new Image(getClass().getResourceAsStream("/org/tudogostoso/Imagens/fotoDefaultReceitas.jpg"), 50, 50, false, true);
                }
                imageViewReceita.setImage(image);
                setGraphic(hBoxReceita);
            } else {
                setGraphic(null);
            }
        }
    }

    private void carregarMinhasReceitas() {
        minhasReceitasList.setAll(controle.buscarReceitaPorAutor(usuario));
    }

    @FXML
    void handleBotaoAdicionarReceita(ActionEvent event) {
        Sessao.setUltimaCena("minhasReceitas");
        gerenciadorTelas.mudarTela("criarReceitas", event);
    }

    @FXML
    void handleBotaoExcluirReceita() {
        Receita receitaSelecionada = listViewMinhasReceitas.getSelectionModel().getSelectedItem();

        if (receitaSelecionada != null) {
            controle.excluirMinhaReceita(usuario, receitaSelecionada);
            carregarMinhasReceitas();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma Receita Selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma receita para excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleBotaoVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }

    @FXML
    void handleBotaoEditar(ActionEvent event) {
        Receita receitaSelecionada = listViewMinhasReceitas.getSelectionModel().getSelectedItem();

        if (receitaSelecionada != null) {
            Sessao.setReceitaSessao(receitaSelecionada);
            gerenciadorTelas.mudarTela("editarReceita", event); // Tela para editar receita
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma Receita Selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma receita para editar.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleBotaoVerMais(ActionEvent event) {
        Receita receitaSelecionada = listViewMinhasReceitas.getSelectionModel().getSelectedItem();


        if (receitaSelecionada != null) {
            Sessao.setReceitaSessao(receitaSelecionada);
            gerenciadorTelas.mudarTela("receita", event); // Tela para ver mais detalhes da receita
            Sessao.setUltimaCena("minhasReceitas");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma Receita Selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma receita para ver mais detalhes.");
            alert.showAndWait();
        }
    }
}
