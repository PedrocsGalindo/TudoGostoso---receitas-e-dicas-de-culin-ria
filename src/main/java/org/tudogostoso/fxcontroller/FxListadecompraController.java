package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Ingrediente;
import org.tudogostoso.modelo.Usuario;

import java.util.List;

public class FxListadecompraController {

    @FXML
    private HBox lista;

    @FXML
    private TextField inputItem;
    @FXML
    private TextField inputItem2;
    @FXML
    private TextField inputItem3;

    private Usuario usuarioAtual;


    private final Controle controle = ControleFactory.criarControleGeral();
    private final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        carregarListaDeCompras();
    }

    private void carregarListaDeCompras() {
        if (lista != null) {
            lista.getChildren().clear();
            if (usuarioAtual != null) {
                List<Ingrediente> listaDeCompras = usuarioAtual.getListaDeCompra();
                for (Ingrediente item : listaDeCompras) {
                    Text itemText = new Text(item.getNome());
                    lista.getChildren().add(itemText);
                }
            }
        }
    }
    @FXML
    void handllerBotaoVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);

    }

    @FXML
    private void adicionarItem() {
        String nomeNovoItem = inputItem.getText().trim();

        if (!nomeNovoItem.isEmpty() && usuarioAtual != null) {
            Ingrediente novoIngrediente = new Ingrediente(nomeNovoItem);
            controle.adicionarItemListaCompras(usuarioAtual, novoIngrediente);
            carregarListaDeCompras();
            inputItem.clear();
        }
    }
}

