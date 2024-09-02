package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.*;


import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FxReceitaController {

    @FXML
    private TextArea textAreaAvaliacoes, textAreaIngrediente, textAreaTitulo, textAreaNota, textAreaPreparo;

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

        textAreaTitulo.setText(receita.getTitulo());

        String nota = String.valueOf(receita.getNota());
        textAreaNota.setText(nota + "/5");

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
    }

    @FXML
    void handllerBotaoAddFav(ActionEvent event) {
        controle.addReceitafav(usuario, receita);
    }

    @FXML
    void handllerBotaoAvaliar(ActionEvent event) {
        String comentario = textFiledComentario.getText();
        int nota = choiceBoxNota.getValue();
        try {
            controle.criarAvaliacao(nota, comentario, usuario, receita);
            setComentarios(receita);
            textAreaNota.setText(String.valueOf(receita.getNota() + "/5"));

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

