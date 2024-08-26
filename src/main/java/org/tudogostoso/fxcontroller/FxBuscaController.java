package org.tudogostoso.fxcontroller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.controle.Controle;

import javafx.fxml.FXML;
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
    private TextArea textArea1, textArea2, textArea3, textArea4;

    @FXML
    private ImageView imageView1, imageView2, imageView3, imageView4;

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

    @FXML
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkBoxPorNome.isSelected() || checkBoxPorAutor.isSelected() || checkBoxPorIngrediente.isSelected() || checkBoxPorAvaliacao.isSelected() ) {
                String textoDigitado = textFildBusca.getText();
                if (checkBoxPorNome.isSelected()){
                    List<Receita> receitas = controle.buascarReceitaPorTitulo(textoDigitado);
                    if (!receitas.isEmpty()) {
                        Collections.sort(receitas);
                        Collections.reverse(receitas);
                        }
                        try {
                            //Vai ser aq que vai passar a receita para a sessão para ela puder ser acessada na cena de Receita
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
                }else if (checkBoxPorAutor.isSelected() ){

                }else if (checkBoxPorIngrediente.isSelected()){

                }else if (checkBoxPorAvaliacao.isSelected()){

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
        root = FXMLLoader.load(getClass().getResource("/org/tudogostoso/telas/receita.fxml"));
        scene = new Scene(root);

        // Obtenha a Stage a partir do evento
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
