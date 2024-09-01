package org.tudogostoso.telas;

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


    private Controle controle;

    @FXML
    public void initialize() {

        controle = ControleFactory.criarControleGeral();
        carregarListaDeCompras();
    }

    private void carregarListaDeCompras() {
        lista.getChildren().clear();
        if (usuarioAtual != null) {
            List<Ingrediente> listaDeCompras = usuarioAtual.getListaDeCompra();
            for (Ingrediente item : listaDeCompras) {
                Text itemText = new Text(item.getNome());
                lista.getChildren().add(itemText);
            }
        }
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

