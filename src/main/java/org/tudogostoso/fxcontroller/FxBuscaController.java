package org.tudogostoso.fxcontroller;


import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.modelo.Sessao;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FxBuscaController {

    @FXML
    private TextField textFildBusca;
    private ContextMenu sugestoes;

    @FXML
    private CheckBox checkBoxPorNome, checkBoxPorAutor, checkBoxPorIngrediente, checkBoxPorAvaliacao, checkBoxPorCategoria;

    @FXML
    private GridPane receitaGridPane1, receitaGridPane2, receitaGridPane3, receitaGridPane4;

    @FXML
    private CheckBox focus;

    private static final Controle controle = ControleFactory.criarControleGeral();
    private GridPane[] receitasGridPane;
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();
    private List<String> listaDeSugestoes;

    @FXML
    public void initialize() {
        receitasGridPane = new GridPane[]{receitaGridPane1, receitaGridPane2, receitaGridPane3, receitaGridPane4};
        for (GridPane gridPane : receitasGridPane) {
            gridPane.setMouseTransparent(true);
        }
    }

    @FXML
    void botaoVoltar(ActionEvent event){
        gerenciadorTelas.mudarTela("feed", event);
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
    void enquantoDigita() {

        if (sugestoes == null) {
            sugestoes = new ContextMenu();
        }

        sugestoes.getItems().clear();

        if (checkBoxPorNome.isSelected() || checkBoxPorAutor.isSelected() || checkBoxPorIngrediente.isSelected() || checkBoxPorAvaliacao.isSelected() || checkBoxPorCategoria.isSelected()) {
            String textoDigitado = textFildBusca.getText();

            if (checkBoxPorNome.isSelected()) {
                listaDeSugestoes = controle.sugestaoReceitaPorTitulo(textoDigitado);
            } else if (checkBoxPorAutor.isSelected()) {
                listaDeSugestoes = controle.sugestaoReceitaPorAutor(textoDigitado);
            } else if (checkBoxPorIngrediente.isSelected()) {
                listaDeSugestoes = controle.sugestaoReceitaPorIngrediente(textoDigitado);
            } else if (checkBoxPorCategoria.isSelected()) {
                listaDeSugestoes = controle.sugestaoReceitaPorCategoria(textoDigitado);
            }

            // Adiciona as sugestões ao ContextMenu
            if (listaDeSugestoes != null && !listaDeSugestoes.isEmpty()) {
                for (String sugestao : listaDeSugestoes) {
                    MenuItem item = new MenuItem(sugestao);
                    //evento de seleção
                    item.setOnAction(e -> {
                        textFildBusca.setText(sugestao);
                        sugestoes.hide();
                    });
                    sugestoes.getItems().add(item);
                }

                // A posição do ContextMenu definida não influencia onde ele aparecera na tela
                double x = textFildBusca.localToScene(0, 0).getX() + textFildBusca.getScene().getWindow().getX() + textFildBusca.getLayoutX();
                double y = textFildBusca.localToScene(0, 0).getY() + textFildBusca.getScene().getWindow().getY() + textFildBusca.getLayoutY() + textFildBusca.getHeight();

                sugestoes.show(textFildBusca, x, y - 10);
            } else {
                sugestoes.hide(); //se não houver sugestões
            }
        } else {
            sugestoes.hide(); //se nenhum checkbox estiver selecionado
        }
    }

    @FXML
    private void handleEnterKey(KeyEvent event) {
        limpar(receitasGridPane);

        List<Receita> receitas = new ArrayList<>();

        if (event.getCode() == KeyCode.ENTER) {

            if (checkBoxPorNome.isSelected() || checkBoxPorAutor.isSelected() || checkBoxPorIngrediente.isSelected() || checkBoxPorAvaliacao.isSelected() || checkBoxPorCategoria.isSelected() ) {
                String textoDigitado = textFildBusca.getText();

                if (checkBoxPorNome.isSelected()){
                    receitas = controle.buascarReceitaPorTitulo(textoDigitado);
                }else if (checkBoxPorAutor.isSelected() ){
                    receitas = controle.buscarReceitaPorAutor(textoDigitado);
                }else if (checkBoxPorIngrediente.isSelected()){
                    receitas = controle.buscarReceitaPorIngrediente(textoDigitado);
                }else if (checkBoxPorAvaliacao.isSelected()){
                    int nota = Integer.parseInt(textoDigitado);
                    receitas = controle.buscarReceitaPorAvaliacao(nota);
                } else if (checkBoxPorCategoria.isSelected()) {
                    receitas = controle.buscarReceitaPorCategoria(textoDigitado);
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
    void clicarReceitaGridPane(MouseEvent event){

        //associa a receita do gridClicado à receita da sessao
        Node noClique = (Node) event.getSource();
        GridPane gridClicado = (GridPane) noClique;
        Sessao.setReceitaSessao(receitaAssociadaAoGridPane(gridClicado));

        Sessao.setUltimaCena("buscar");

        gerenciadorTelas.mudarTela("receita", event);
    }
    @FXML
    void mousePorCimaBotao(MouseEvent event) {
        Button botao = (Button) event.getSource();
        botao.setStyle("-fx-background-color:  #5aa55a;");
    }

    @FXML
    void mousePorFora(MouseEvent event) {
        Button botao = (Button) event.getSource();
        botao.setStyle("-fx-background-color:   #90ee90;");
    }

    @FXML
    void cliqueAutor() {
        checkBoxPorAutor.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);
        checkBoxPorCategoria.setSelected(false);

    }
    @FXML
    void cliqueAvaliacao() {
        checkBoxPorAvaliacao.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorCategoria.setSelected(false);
    }
    @FXML
    void cliqueIngrediente() {
        checkBoxPorIngrediente.setSelected(true);
        checkBoxPorNome.setSelected(false);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);
        checkBoxPorCategoria.setSelected(false);
    }
    @FXML
    void cliqueCategoria(){
        checkBoxPorCategoria.setSelected(true);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorNome.setSelected(false);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);
    }
    @FXML
    void cliqueNome() {
        checkBoxPorNome.setSelected(true);
        checkBoxPorAutor.setSelected(false);
        checkBoxPorIngrediente.setSelected(false);
        checkBoxPorAvaliacao.setSelected(false);
        checkBoxPorCategoria.setSelected(false);

    }

}
