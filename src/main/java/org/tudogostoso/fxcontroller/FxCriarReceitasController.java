package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Text textUnidadeDeMedida;

    @FXML
    private ImageView imagemEscolhida;

    @FXML
    private ChoiceBox<UnidadeMedida> choiceBoxUnidadeDeMedida;

    @FXML
    private TextField textFieldQuantidade, textFieldTitulo, textFieldTempodDePreapro,textFieldCateogira, textFieldNomeIngrediente;

    @FXML
    private TextField textFieldIngrediente;
    private ContextMenu sugestoes;

    @FXML
    private ListView<ItemIngrediente> listViewItemnsIngredientes;

    @FXML
    private TextArea textAreaPreparo;

    List<ItemIngrediente> itemIngredientes = new ArrayList<>();
    private final Controle controle = ControleFactory.criarControleGeral();
    private File caminhoArquivoUsuario;
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {

        List<UnidadeMedida> unidadesDeMedida = controle.buscarUnidadeDeMedida();
        ObservableList<UnidadeMedida> observableUnidadesDeMedida = FXCollections.observableArrayList(unidadesDeMedida);

        choiceBoxUnidadeDeMedida.setItems(observableUnidadesDeMedida);

        imagemEscolhida.setImage(new Image(new File("src/main/resources/org/tudogostoso/Imagens/fotoDefaultReceitas.jpg").getAbsolutePath()));

        choiceBoxUnidadeDeMedida.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se um valor foi selecionado
            if (newValue != null) {
                textUnidadeDeMedida.setVisible(false); // Oculta o texto
            }
        });
    }

    @FXML
    void enquantoDigita() {

        if (sugestoes == null) {
            sugestoes = new ContextMenu();
        }
        String textoDigitado = textFieldIngrediente.getText();
        sugestoes.getItems().clear();
        List<String> listaDeSugestoes = controle.sugestaoIngrediente(textoDigitado);


        // Adiciona as sugestões ao ContextMenu
        if (listaDeSugestoes != null && !listaDeSugestoes.isEmpty()) {
            for (String sugestao : listaDeSugestoes) {
                MenuItem item = new MenuItem(sugestao);
                //evento de seleção
                item.setOnAction(e -> {
                    textFieldIngrediente.setText(sugestao);
                    sugestoes.hide();
                });
                sugestoes.getItems().add(item);
            }

            // A posição do ContextMenu definida não influencia onde ele aparecera na tela
            double x = textFieldIngrediente.localToScene(0, 0).getX()
                    + textFieldIngrediente.getScene().getWindow().getX()
                    + textFieldIngrediente.getLayoutX();

            double y = textFieldIngrediente.localToScene(0, 0).getY()
                    + textFieldIngrediente.getScene().getWindow().getY()
                    + textFieldIngrediente.getLayoutY()
                    + textFieldIngrediente.getHeight();

            sugestoes.show(textFieldIngrediente, x, y - 177);
        } else {
            sugestoes.hide(); //se não houver sugestões
        }
    }

    @FXML
    void HandllerButtonVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela(Sessao.getUltimaCena(),event);
    }

    @FXML
    void handllerButtonAdicionarItemIngrediente() {
        //pega o ingrediente e a unidade de medida
        Ingrediente ingrediente = controle.buscarIngredientePorNome(textFieldIngrediente.getText());
        UnidadeMedida unidadeMedida = choiceBoxUnidadeDeMedida.getValue();
        try {
            //cria o Item ingrediente
            double quantidade = Double.parseDouble(textFieldQuantidade.getText());
            ItemIngrediente itemIngrediente = controle.criarItemIngrediente(ingrediente, quantidade, unidadeMedida);

            //mostra o item criado
            listViewItemnsIngredientes.getItems().add(itemIngrediente);
            itemIngredientes.add(itemIngrediente);

            //limpa os valores do item ingrediente
            textFieldIngrediente.clear();
            choiceBoxUnidadeDeMedida.setValue(null);
            textFieldQuantidade.clear();
            textUnidadeDeMedida.setVisible(true);

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
            controle.criarIngrediente(nomeIngrediente);
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

        //valores necessarios para crair uma receita
        UsuarioChef usuarioChef = (UsuarioChef) Sessao.getUsuarioSessao();
        String titulo = textFieldTitulo.getText();
        String tempoPreparo = textFieldTempodDePreapro.getText();
        String categoria = textFieldCateogira.getText();

        // separa o array usando a quebra de linha como parametro
        List<String> preparo = Arrays.asList(textAreaPreparo.getText().split("\n"));

        System.out.println(caminhoArquivoUsuario);

        try {
            //se o usuario tiver selecionado uma imagem
            if (caminhoArquivoUsuario != null) {
                String caminhoImagem = controle.salvarImagem(caminhoArquivoUsuario, usuarioChef.getNome() + titulo.replace(" ", ""));
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria, caminhoImagem);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Receita criada com sucesso", "Sua Receita "+ titulo +" foi criada com sucesso");

            } else {
                controle.criarReceita(titulo, usuarioChef, itemIngredientes, preparo, tempoPreparo, categoria);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Receita criada com sucesso", "Sua Receita "+ titulo +" foi criada com sucesso");
            }
            gerenciadorTelas.mudarTela("feed", event);
        }catch (IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar imagem", "Erro ao salvar imagem: " +  e.getMessage());
        } catch (NullPointerException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Campo Vazio", "Algum dos campos esta vazio");
        } catch (ReceitaJaExistenteException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Receita ja existe", "Voce ja possui Uma receita com esse mesmo titulo");
        }

    }

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
                    // Nome do arquivo para salvar a imagem
                    String nomeArquivo = "imagemSelecionada_" + System.currentTimeMillis() + ".jpg"; // Nome único baseado no timestamp

                    // Salva a imagem no diretório especificado
                    try {
                        String caminhoImagem = GoogleImagens.salvarImagem(urlImagem, nomeArquivo);
                        Image imagem = new Image(new File(caminhoImagem).toURI().toString());
                        imagemEscolhida.setImage(imagem);
                        caminhoArquivoUsuario = new File(caminhoImagem); // Atualiza o caminho da imagem na variável
                    } catch (IOException e) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar imagem", "Erro ao salvar imagem: " + e.getMessage());
                    }
                });

            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao buscar imagem", "Erro ao buscar imagem: " + e.getMessage());
            }
        });
    }

    @FXML
    void keyPressedListView(KeyEvent event) {
        //para de mostrar o item selecionado e o retira da lista de itensIngrediente
        if (event.getCode() == KeyCode.BACK_SPACE){
            ItemIngrediente itemSelecionado = listViewItemnsIngredientes.getSelectionModel().getSelectedItem();
            listViewItemnsIngredientes.getItems().remove(itemSelecionado);
            itemIngredientes.remove(itemSelecionado);
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

