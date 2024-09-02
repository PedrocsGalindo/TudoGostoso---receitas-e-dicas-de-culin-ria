package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Ingrediente;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

public class FxlistaDeComprasVController {

    @FXML
    private ListView<Ingrediente> listaCompras;

    @FXML
    private TextField ingredienteField;

    @FXML
    private Button botaoExcluirIngrediente;

    private ObservableList<Ingrediente> CompraList;
    private Controle controle = ControleFactory.criarControleGeral();
    private FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        CompraList = FXCollections.observableArrayList();
        listaCompras.setItems(CompraList);
        listaCompras.setCellFactory(param -> new IngredienteListCell());
        carregarListaDeCompras();
    }

    private void carregarListaDeCompras() {
        Usuario usuario1 = Sessao.getUsuarioSessao();
        CompraList.setAll(usuario1.getListaDeCompra());
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
    private void excluirIngrediente(ActionEvent event) {
        Ingrediente ingredienteSelecionado = listaCompras.getSelectionModel().getSelectedItem();
        if (ingredienteSelecionado != null) {
            CompraList.remove(ingredienteSelecionado);
            Usuario usuario1 = Sessao.getUsuarioSessao();
            usuario1.getListaDeCompra().remove(ingredienteSelecionado);
            // Se você estiver salvando a lista de compras em um arquivo ou banco de dados, atualize-o aqui
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Ingrediente Selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um ingrediente para excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    void voltarParaFeed(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }

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
