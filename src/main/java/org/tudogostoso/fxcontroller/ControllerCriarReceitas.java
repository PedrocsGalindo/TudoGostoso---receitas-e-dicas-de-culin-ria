package org.tudogostoso.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ControllerCriarReceitas {

    @FXML
    private Button arquivos;

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

}

