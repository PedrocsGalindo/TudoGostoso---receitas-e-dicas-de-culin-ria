package org.tudogostoso.fxcontroller;

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

import java.io.File;
import java.util.Collections;
import java.util.List;

public class FxBuscaController {

    public static Controle controle = ControleFactory.criarControleGeral();

    @FXML
    private TextField textFildBusca;

    @FXML
    private CheckBox checkBoxPorNome;

    @FXML
    private CheckBox checkBoxPorAutor;

    @FXML
    private CheckBox checkBoxPorIngrediente;

    @FXML
    private CheckBox checkBoxPorAvaliacao;

    @FXML
    private TextArea textArea1;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private TextArea textArea2;

    @FXML
    private ImageView imageView3;

    @FXML
    private TextArea textArea3;

    @FXML
    private ImageView imageView4;

    @FXML
    private TextArea textArea4;

    @FXML
    private CheckBox focus;

    public void prencher(Receita receita, ImageView imagem, TextArea texto){
        texto.setText(receita.getTitulo() + "\n" + receita.getAutor().getNome());
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
                        try {
                            prencher(receitas.get(0),imageView1, textArea1);
                            prencher(receitas.get(1),imageView2, textArea2);
                            prencher(receitas.get(2),imageView3, textArea3);
                            prencher(receitas.get(3),imageView4, textArea4);
                        } catch (IndexOutOfBoundsException e) {
                            //não possui 4 receitas
                        }

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
