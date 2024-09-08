package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.*;


import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FxReceitaController {

    @FXML
    private TextArea textAreaAvaliacoes, textAreaIngrediente, textAreaPreparo;

    @FXML
    private Text textTitulo, textNota;

    @FXML
    private ImageView imagemReceita;

    @FXML
    private ChoiceBox<Integer> choiceBoxNota;

    @FXML
    private TextField textFiledComentario;

    List<ItemIngrediente> ingredientes;
    private final Controle controle = ControleFactory.criarControleGeral();
    Usuario usuario = Sessao.getUsuarioSessao();
    Receita receita = Sessao.getReceitaSessao();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    public void setComentarios(Receita receita){
        textAreaAvaliacoes.clear();
        StringBuilder stringAvaliacoes = new StringBuilder();
        List<Avaliacao> avaliacaos = receita.getAvaliacoes();
        if (!avaliacaos.isEmpty()) {
            for (Avaliacao avaliacao : avaliacaos) {
                stringAvaliacoes.append(avaliacao.getComentario()).append("\nNota: ").append(avaliacao.getNota()).append("\n");
            }
            textAreaAvaliacoes.setText(stringAvaliacoes.toString());
        }
    }

    @FXML
    public void initialize() {

        setComentarios(receita);

        imagemReceita.setImage(new Image(new File(receita.getCaminhoImagem()).getAbsolutePath()));

        //setar os ingredientes
        StringBuilder stringIngrediente = new StringBuilder();
        ingredientes = receita.getIngredientes();
        if (!ingredientes.isEmpty()) {
            for (ItemIngrediente ingrediente : ingredientes) {
                stringIngrediente.append(ingrediente.getIngrediente().getNome()).append(": ").append(ingrediente.getQuantidade()).append(" ").append(ingrediente.getMedida()).append("\n\n");
            }
            textAreaIngrediente.setText(stringIngrediente.toString());
        }

        textTitulo.setText(receita.getTitulo());

        String nota = String.valueOf(receita.getNota());
        textNota.setText(nota + "/5");

        String preparo = String.join("\n\n " , receita.getPreparo());
        textAreaPreparo.setText(preparo);

        ObservableList<Integer> opçõesNota = FXCollections.observableArrayList(Arrays.asList(0, 1, 2, 3, 4, 5));
        choiceBoxNota.setValue(5);
        choiceBoxNota.setItems(opçõesNota);

    }

    @FXML
    void handllerBotaoAddListaCompra(ActionEvent event) {
        for (ItemIngrediente itemIngrediente : ingredientes) {
            usuario.addListaDeCompra(itemIngrediente.getIngrediente());
        }
        controle.atualizarUsuario(usuario);
        Button botao = (Button) event.getSource();
        botao.setDisable(true);
        botao.setVisible(false);
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
    void handllerBotaoAddFav(ActionEvent event) {
        controle.addReceitafav(usuario, receita);
        //pegando a fonte do evento, nesse caso o botAddFav
        Button botao = (Button) event.getSource();
        botao.setDisable(true);
        botao.setVisible(false);
    }

    @FXML
    void handllerBotaoAvaliar(ActionEvent event) {
        String comentario = textFiledComentario.getText();
        int nota = choiceBoxNota.getValue();
        try {
            controle.criarAvaliacao(nota, comentario, usuario, receita);
            setComentarios(receita);
            textNota.setText(String.valueOf(receita.getNota() + "/5"));

        } catch (NullPointerException e) {
            Alert alertaCriarAvaliacao = new Alert(Alert.AlertType.ERROR);
            alertaCriarAvaliacao.setTitle("Erro Avaliação");
            alertaCriarAvaliacao.setHeaderText(e.getMessage());
            alertaCriarAvaliacao.setContentText("Por favor, insira uma avaliação válido.");
            alertaCriarAvaliacao.showAndWait();
        }
    }

    @FXML
    void handllerBotaoVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela(Sessao.getUltimaCena(), event);

    }
}

