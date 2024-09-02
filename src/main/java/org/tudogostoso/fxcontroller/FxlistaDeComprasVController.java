package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Ingrediente;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

import java.util.List;

public class FxlistaDeComprasVController {

    @FXML
    private ListView<Ingrediente> listaCompras;

    @FXML
    private TextField ingredienteField;

    private ObservableList<Ingrediente> CompraList;
    private Controle controle = ControleFactory.criarControleGeral();
    private FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        CompraList = FXCollections.observableArrayList();
        listaCompras.setItems(CompraList);
        listaCompras.setCellFactory(new Callback<ListView<Ingrediente>, ListCell<Ingrediente>>() {
            @Override
            public ListCell<Ingrediente> call(ListView<Ingrediente> param) {
                return new IngredienteListCell(); // Classe de célula personalizada
            }
        });
        carregarListaDeCompras();
    }

    private void carregarListaDeCompras() {
        Usuario usuario = Sessao.getUsuarioSessao();
        if (usuario != null) {
            List<Ingrediente> listaDeCompra = usuario.getListaDeCompra();
            if (listaDeCompra != null) {
                CompraList.setAll(listaDeCompra);
            } else {
                CompraList.clear();
            }
        }
    }

    @FXML
    private void adicionarIngrediente(ActionEvent event) {
        String nomeIngrediente = ingredienteField.getText().trim();
        if (!nomeIngrediente.isEmpty()) {
            Ingrediente novoIngrediente = new Ingrediente(nomeIngrediente);
            CompraList.add(novoIngrediente);
            Usuario usuario1 = Sessao.getUsuarioSessao();
            usuario1.addListaDeCompra(novoIngrediente);
            ingredienteField.clear();
        }
    }


    @FXML
    void voltarParaFeed(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }

    // Classe interna para a célula personalizada da ListView
    private class IngredienteListCell extends ListCell<Ingrediente> {
        @Override
        protected void updateItem(Ingrediente item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                setText(item.getNome());
            } else {
                setText(null);
            }
        }
    }
}
