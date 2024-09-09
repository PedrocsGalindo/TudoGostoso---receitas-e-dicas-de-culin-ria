package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
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
    private Text textIngrediente, textUnidadeDeMedida;

    @FXML
    private ImageView imagemEscolhida;

    @FXML
    private ChoiceBox<Ingrediente> choicheBoxIngrediente;
    @FXML
    private ChoiceBox<UnidadeMedida> choiceBoxUnidadeDeMedida;

    @FXML
    private TextField textFieldQuantidade, textFieldTitulo, textFieldTempodDePreapro,textFieldCateogira, textFieldNomeIngrediente;

    @FXML
    private TextArea textAreaItemnsIngredientes, textAreaPreparo;

    List<ItemIngrediente> itemIngredientes = new ArrayList<>();
    private final Controle controle = ControleFactory.criarControleGeral();
    private File caminhoArquivoUsuario;
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

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

        choicheBoxIngrediente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se um valor foi selecionado
            if (newValue != null) {
                textIngrediente.setVisible(false); // Oculta o texto
            }
        });
        choiceBoxUnidadeDeMedida.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se um valor foi selecionado
            if (newValue != null) {
                textUnidadeDeMedida.setVisible(false); // Oculta o texto
            }
        });
    }

    @FXML
    void HandllerButtonVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela(Sessao.getUltimaCena(),event);
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
            choicheBoxIngrediente.setValue(null);
            choiceBoxUnidadeDeMedida.setValue(null);
            textFieldQuantidade.clear();
            textUnidadeDeMedida.setVisible(true);
            textIngrediente.setVisible(true);

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
    void handllerButtonCriarReceita(ActionEvent event) {
        UsuarioChef usuarioChef = (UsuarioChef) Sessao.getUsuarioSessao();
        String titulo = textFieldTitulo.getText();
        String tempoPreparo = textFieldTempodDePreapro.getText();
        String categoria = textFieldCateogira.getText();
        //tem que separar cada etapa por linha
        List<String> preparo = Arrays.asList(textAreaPreparo.getText().split("\n"));
        try {
            if (caminhoArquivoUsuario.exists() && caminhoArquivoUsuario != null) {
                String caminhoImagem = controle.salvarImagem(caminhoArquivoUsuario, usuarioChef.getNome() + titulo.replace(" ", ""));
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria, caminhoImagem);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Receita criada com sucesso", "Sua Receita "+ titulo +" foi criada com sucesso");
            } else {
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Receita criada com sucesso", "Sua Receita "+ titulo +" foi criada com sucesso");
            }
            limparTela();
            gerenciadorTelas.mudarTela("feed", event);
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
    private void limparTela(){
        imagemEscolhida.setImage(new Image(new File("src/main/resources/org/tudogostoso/Imagens/fotoDefaultReceitas.jpg").getAbsolutePath()));
        textAreaItemnsIngredientes.clear();
        textFieldCateogira.clear();
        textAreaPreparo.clear();
        textFieldQuantidade.clear();
        textFieldTempodDePreapro.clear();
        textFieldTitulo.clear();
        choicheBoxIngrediente.setValue(null);
        choiceBoxUnidadeDeMedida.setValue(null);
        caminhoArquivoUsuario = null;
    }
    //Vou testar ainda e ajustar
    @FXML
    void handleBuscarImagemWeb() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Imagem no Google");
        dialog.setHeaderText("Digite o termo de pesquisa para buscar imagens:");
        dialog.setContentText("Pesquisa:");

        dialog.showAndWait().ifPresent(termoPesquisa -> {
            try {
                // Número de imagens que você deseja buscar
                int numImagens = 5;

                // Chama a classe GoogleImagens para buscar múltiplas imagens
                List<String> urlsImagens = GoogleImagens.buscarImagens(termoPesquisa, numImagens);

                // Criar uma lista de escolha para o usuário selecionar uma das imagens
                ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(urlsImagens.get(0), urlsImagens);
                choiceDialog.setTitle("Escolha uma Imagem");
                choiceDialog.setHeaderText("Selecione a imagem desejada:");
                choiceDialog.setContentText("Imagens:");

                choiceDialog.showAndWait().ifPresent(urlImagem -> {
                    Image imagem = new Image(urlImagem);
                    imagemEscolhida.setImage(imagem);
                });

            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao buscar imagem", "Erro ao buscar imagem: " + e.getMessage());
            }
        });
        }
}

