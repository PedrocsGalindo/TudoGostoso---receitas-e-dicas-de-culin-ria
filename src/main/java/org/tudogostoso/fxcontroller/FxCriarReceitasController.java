package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.exceptions.ObjetoJaExiste;
import org.tudogostoso.exceptions.ReceitaJaExistenteException;
import org.tudogostoso.modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FxCriarReceitasController {

    @FXML
    private Button arquivos;

    @FXML
    private ImageView imagemEscolhida;

    @FXML
    private ChoiceBox<Ingrediente> choicheBoxIngrediente;
    @FXML
    private ChoiceBox<UnidadeMedida> choiceBoxUnidadeDeMedida;

    @FXML
    private TextField textFieldQuantidade, textFieldPreparo, textFieldTitulo, textFieldTempodDePreapro,textFieldCateogira, textFieldNomeIngrediente;

    @FXML
    private TextArea textAreaItemnsIngredientes;

    List<ItemIngrediente> itemIngredientes = new ArrayList<>();
    private final Controle controle = ControleFactory.criarControleGeral();
    private File caminhoArquivoUsuario;

    @FXML
    public void initialize() {
        Sessao.setUsuarioSessao(controle.recuperarUsuarioPorId(3));
        List<Ingrediente> ingredientes = controle.buscarIngrediente();
        ObservableList<Ingrediente> observableIngredientes = FXCollections.observableArrayList(ingredientes);
        List<UnidadeMedida> unidadesDeMedida = controle.buscarUnidadeDeMedida();
        ObservableList<UnidadeMedida> observableUnidadesDeMedida = FXCollections.observableArrayList(unidadesDeMedida);

        choicheBoxIngrediente.setItems(observableIngredientes);
        choiceBoxUnidadeDeMedida.setItems(observableUnidadesDeMedida);

        imagemEscolhida.setImage(new Image(new File("src/main/resources/org/tudogostoso/Imagens/fotoDefaultReceitas.jpg").getAbsolutePath()));
    }

    @FXML
    void HandllerButtonVoltar() {

    }

    @FXML
    void handllerButtonAdicionarItemIngrediente() {
        Ingrediente ingrediente = choicheBoxIngrediente.getValue();
        UnidadeMedida unidadeMedida = choiceBoxUnidadeDeMedida.getValue();
        try {
            double quantidade = Double.parseDouble(textFieldQuantidade.getText());
            ItemIngrediente itemIngrediente = controle.criarItemIngrediente(ingrediente, quantidade, unidadeMedida);

            String textoItem = textAreaItemnsIngredientes.getText();
            textoItem = textoItem + itemIngrediente + "\n";
            textAreaItemnsIngredientes.setText(textoItem);
            itemIngredientes.add(itemIngrediente);

        }catch (NullPointerException e ){
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vazio", "Algum dos campos para adicionar item esta vazio");
        } catch (NumberFormatException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Dado invalido", "Preencha a quantidade com um numero valido");
        }


    }

    @FXML
    void handllerButtonCriarIngrediente() {
        try {
            String nomeIngrediente = textFieldNomeIngrediente.getText();
            Ingrediente ingrediente = controle.criarIngrediente(nomeIngrediente);
            ObservableList<Ingrediente> lista = choicheBoxIngrediente.getItems();
            lista.add(ingrediente);
            textFieldNomeIngrediente.clear();

        } catch (ObjetoJaExiste e ) {
            mostrarAlerta(Alert.AlertType.ERROR, "Ingrediente Já existe", "Esse ingrediente ja existe, procure na lista de ingrediente a cima");
        }catch (NullPointerException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vazio", "Preencha o campo com o nome do ingrediente");
        }
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
        caminhoArquivoUsuario = fileChooser.showOpenDialog(stage);
        if (caminhoArquivoUsuario != null) {
            Image imagem = new Image(caminhoArquivoUsuario.toURI().toString());
            imagemEscolhida.setImage(imagem);
        }


    }
    @FXML
    void handllerButtonCriarReceita() {
        UsuarioChef usuarioChef = (UsuarioChef) Sessao.getUsuarioSessao();
        String titulo = textFieldTitulo.getText();
        String tempoPreparo = textFieldTempodDePreapro.getText();
        String categoria = textFieldCateogira.getText();
        //tem que separar cada etapa por linha
        List<String> preparo = Arrays.asList(textFieldPreparo.getText().split("\n"));
        try {
            if (caminhoArquivoUsuario.exists() && caminhoArquivoUsuario != null) {
                String caminhoImagem = controle.salvarImagem(caminhoArquivoUsuario, usuarioChef.getNome() + titulo.replace(" ", ""));
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria, caminhoImagem);
            } else {
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria);
            }
        }catch (IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar imagem", "Erro ao salvar imagem: " +  e.getMessage());
        } catch (NullPointerException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Campo Vazio", "Algum dos campos esta vazio");
        } catch (ReceitaJaExistenteException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Receita ja existe", "Voce ja possui Uma receita com esse mesmo titulo");
        }

    }
    private void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}

