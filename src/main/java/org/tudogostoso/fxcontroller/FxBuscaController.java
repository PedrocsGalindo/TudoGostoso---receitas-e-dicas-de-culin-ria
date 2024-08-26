package org.tudogostoso.fxcontroller;


import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.modelo.Sessao;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FxBuscaController {

    @FXML
    private TextField textFildBusca;

    @FXML
    private CheckBox checkBoxPorNome, checkBoxPorAutor, checkBoxPorIngrediente, checkBoxPorAvaliacao;

    @FXML
    private GridPane receitaGridPane1, receitaGridPane2, receitaGridPane3, receitaGridPane4;

    @FXML
    private CheckBox focus;

    private static Controle controle = ControleFactory.criarControleGeral();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GridPane[] receitasGridPane;

    @FXML
    public void initialize() {
        receitasGridPane = new GridPane[]{receitaGridPane1, receitaGridPane2, receitaGridPane3, receitaGridPane4};
        for (GridPane gridPane : receitasGridPane) {
            gridPane.setMouseTransparent(true);
        }
    }

    public void prencher(Receita receita, GridPane gridPane) throws NullPointerException{
        //deixa clicavel o gridpane
        gridPane.setMouseTransparent(false);

        ImageView imagem = null;
        TextArea texto = null;

        //pega os filhos do grid
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                imagem = (ImageView) node;
            } else if (node instanceof TextArea) {
                texto = (TextArea) node;
            }
        }
        texto.setText(receita.getTitulo() + "\n" + receita.getAutor().getNome());
        texto.setMouseTransparent(true);
        //pega o caminho absoluto do arquivo
        imagem.setImage(new Image(new File(receita.getCaminhoImagem()).getAbsolutePath()));
    }

    public void limpar(GridPane[] receitasGridPane){
        for (GridPane gridPane : receitasGridPane) {
            for (Node node : gridPane.getChildren()) {
                if (node instanceof TextArea) {
                    // Limpar o texto do TextArea
                    ((TextArea) node).clear();
                } else if (node instanceof ImageView) {
                    // Limpar a imagem do ImageView
                    ((ImageView) node).setImage(null);
                }
            }
        }
    }

    public Receita receitaAssociadaAoGridPane(GridPane gridPane){
        Receita receita = null;
        String texto = "vazio";

        if (gridPane.getChildren().get(0) instanceof TextArea){
            texto = ((TextArea) gridPane.getChildren().get(0)).getText();
        } else if (gridPane.getChildren().get(1) instanceof TextArea){
            texto = ((TextArea) gridPane.getChildren().get(1)).getText();
        }
        if (! texto.equals("vazio")){
            String[] linhas = texto.split("\n");

            // Verificar se existem pelo menos duas linhas
            if (linhas.length >= 2) {
                String titulo = linhas[0]; // A primeira linha é o título
                String autor = linhas[1];

                receita =  controle.buscarReceitaPorAutorETitulo(autor, titulo);
            }
        }
        return receita;
    }

    @FXML
    private void handleEnterKey(KeyEvent event) {
        limpar(receitasGridPane);

        List<Receita> receitas = new ArrayList<>();

        if (event.getCode() == KeyCode.ENTER) {

            if (checkBoxPorNome.isSelected() || checkBoxPorAutor.isSelected() || checkBoxPorIngrediente.isSelected() || checkBoxPorAvaliacao.isSelected() ) {
                String textoDigitado = textFildBusca.getText();

                if (checkBoxPorNome.isSelected()){
                    receitas = controle.buascarReceitaPorTitulo(textoDigitado);

                }else if (checkBoxPorAutor.isSelected() ){
                    receitas = controle.buscarReceitaPorAutor(textoDigitado);

                }else if (checkBoxPorIngrediente.isSelected()){

                }else if (checkBoxPorAvaliacao.isSelected()){

                }
                if (!receitas.isEmpty()) {
                    Collections.sort(receitas);
                    Collections.reverse(receitas);
                    try {
                        //Passa as receitas para os gridpanes
                        int i = 0;
                        for(GridPane gridPane : receitasGridPane) {
                            if (gridPane != null){
                                prencher(receitas.get(i),gridPane);
                            }
                            i++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        //não possui 4 receitas
                    }
                }
            } else {
                textFildBusca.clear();
                textFildBusca.setPromptText("escolha uma opção de filtro");
                focus.requestFocus();
            }
        }
    }
    @FXML
    void clicarReceitaGridPane(MouseEvent event) throws IOException {

        Node noClique = (Node) event.getSource();

        //associa a receita do gridClicado à receita da sessao
        GridPane gridClicado = (GridPane) noClique;
        Sessao.setReceitaSessao(receitaAssociadaAoGridPane(gridClicado));

        root = FXMLLoader.load(getClass().getResource("/org/tudogostoso/telas/receita.fxml"));
        scene = new Scene(root);

        // Obtenha a Stage a partir do evento
        stage = (Stage) noClique.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cliqueAutor(MouseEvent event) {
        checkBoxPorAutor.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);

    }

    @FXML
    void cliqueAvaliacao(MouseEvent event) {
        checkBoxPorAvaliacao.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAutor.setSelected(false);
    }

    @FXML
    void cliqueIngrediente(MouseEvent event) {
        checkBoxPorIngrediente.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);
    }

    @FXML
    void cliqueNome(MouseEvent event) {
        checkBoxPorNome.setSelected(true);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);

    }

}
