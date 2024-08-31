package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.io.File;


public class FxCriarReceitasController {

    @FXML
    private Button arquivos;

    @FXML
    private ImageView imagemEscolhida;

    @FXML
    private ChoiceBox<?> choicheBoxIngrediente, choiceBoxUnidadeDeMedida;

    @FXML
    private TextField textFieldQuantidade, textFieldPreparo, textFieldTitulo, textFieldTempodDePreapro, textFieldNomeIngrediente;

    @FXML
    private TextArea textAreaItemnsIngredientes;

    @FXML
    void HandllerButtonVoltar(ActionEvent event) {

    }

    @FXML
    void handllerButtonAdicionarItemIngrediente(ActionEvent event) {

    }

    @FXML
    void handllerButtonCriarIngrediente(ActionEvent event) {

    }

    @FXML
    void arquivosHandle() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione um Arquivo");

        // Filtrar apenas para arquivos de imagem
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        // Abre o FileChooser e obtém o arquivo selecionado
        Stage stage = (Stage) arquivos.getScene().getWindow(); // Obtém a janela atual
        File caminhoArquivoUsuario = fileChooser.showOpenDialog(stage);

        if (caminhoArquivoUsuario != null) {
            // logica para salvar o caminho do arquivo
        }
    }
    @FXML
    void handllerButtonCriarReceita(ActionEvent event) {

    }
}

