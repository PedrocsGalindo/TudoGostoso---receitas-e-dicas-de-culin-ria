package org.tudogostoso.fxcontroller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Collection;
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
    private ImageView ImageView1;

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

    @FXML
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkBoxPorNome.isSelected() || checkBoxPorAutor.isSelected() || checkBoxPorIngrediente.isSelected() || checkBoxPorAvaliacao.isSelected() ) {
                String textoDigitado = textFildBusca.getText();
                if (checkBoxPorNome.isSelected()){
                    List<Receita> receitas = controle.buascarReceitaPorTitulo(textoDigitado);
                    if (receitas.size() > 0) {
                        Collections.sort(receitas);
                        Collections.reverse(receitas);

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

    public void prencher(Receita receita, ImageView imagem, TextArea texto){
        texto.setText(receita.getTitulo() + "/n" + receita.getAutor());
        imagem.getImage();
    }

}
