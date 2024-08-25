package org.tudogostoso.fxcontroller;

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

public class FxBuscaController {

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
            if (checkBoxPorNome.isSelected() == true || checkBoxPorAutor.isSelected() == true || checkBoxPorIngrediente.isSelected() == true || checkBoxPorAvaliacao.isSelected() == true) {
                String textoDigitado = textFildBusca.getText();
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
