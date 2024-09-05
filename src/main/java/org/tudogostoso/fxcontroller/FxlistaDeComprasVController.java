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
        if (usuario1.getListaDeCompra() != null){
            CompraList.setAll(usuario1.getListaDeCompra());
        }
    }

    @FXML
    private void adicionarIngrediente(ActionEvent event) {
        String nomeIngrediente = ingredienteField.getText().trim();
        if (!nomeIngrediente.isEmpty()) {
            Ingrediente novoIngrediente = new Ingrediente(nomeIngrediente);
            if (CompraList.contains(novoIngrediente)) {
                mostrarAlerta(Alert.AlertType.WARNING, "Item ja listado", "O ingrediente que voce quis adicionar ja esta na sua lista de compras");
                ingredienteField.clear();
            }else {
                CompraList.add(novoIngrediente);
                Usuario usuario1 = Sessao.getUsuarioSessao();
                usuario1.addListaDeCompra(novoIngrediente);
                controle.atualizarUsuario(usuario1);
                ingredienteField.clear();
            }
        }
    }

    @FXML
    private void excluirIngrediente(ActionEvent event) {
        Ingrediente ingredienteSelecionado = listaCompras.getSelectionModel().getSelectedItem();
        if (ingredienteSelecionado != null) {
            CompraList.remove(ingredienteSelecionado);
            Usuario usuario1 = Sessao.getUsuarioSessao();
            usuario1.getListaDeCompra().remove(ingredienteSelecionado);
            controle.atualizarUsuario(usuario1);

        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Ingrediente Selecionado", "Por favor, selecione um ingrediente para excluir.");
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
    private void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
