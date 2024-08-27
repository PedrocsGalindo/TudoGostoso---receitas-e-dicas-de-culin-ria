package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.*;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FxReceitaController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextArea textAreaAvaliacoes, textAreaIngrediente, textAreaTitulo, textAreaNota, textAreaPreparo;

    @FXML
    private ImageView imagemReceita;

    @FXML
    private ChoiceBox<Integer> choiceBoxNota;

    @FXML
    private TextField textFiledComentario;

    List<ItemIngrediente> ingredientes;
    Controle controle = ControleFactory.criarControleGeral();
    Usuario usuario = Sessao.getUsuarioSessao();
    Receita receita = Sessao.getReceitaSessao();

    @FXML
    public void initialize() {
        //setar os comentarios
        StringBuilder stringAvaliacoes = new StringBuilder();
        List<Avaliacao> avaliacaos = receita.getAvaliacoes();
        if (!avaliacaos.isEmpty()) {
            for (Avaliacao avaliacao : avaliacaos) {
                stringAvaliacoes.append("\n\n").append(avaliacao.getComentario());
            }
            textAreaAvaliacoes.setText(stringAvaliacoes.toString());
        }

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
        textAreaNota.setText(nota);

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

    }

    @FXML
    void handllerBotaoVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Sessao.getUltimaCena()));
        Scene scene = new Scene(root);

        // Obtenha a Stage a partir do evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}

