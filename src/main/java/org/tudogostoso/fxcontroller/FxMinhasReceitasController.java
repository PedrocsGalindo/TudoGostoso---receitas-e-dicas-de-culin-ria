package org.tudogostoso.fxcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;

public class FxMinhasReceitasController {


    private ObservableList<Receita> minhasReceitasList;
    private Controle controle = ControleFactory.criarControleGeral();
    private FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();
    private Usuario usuario = Sessao.getUsuarioSessao();

    @FXML
    public void initialize() {
        minhasReceitasList = FXCollections.observableArrayList();
        listViewMinhasReceitas.setItems(minhasReceitasList);
        carregarMinhasReceitas();
    }
    private void carregarMinhasReceitas() {
        minhasReceitasList.setAll(controle.buscarReceitaPorAutor(usuario));
    }
    
    @FXML
    private Button botaoVoltar;

    @FXML
    private ListView<Receita> listViewMinhasReceitas;

    @FXML
    private Button botaoAdicionarReceita;

    @FXML
    private Button botaoExcluirReceita;



    @FXML
    void handleBotaoAdicionarReceita(ActionEvent event) {
        Sessao.setUltimaCena("minhasReceitas");
        gerenciadorTelas.mudarTela("criarReceitas", event);
    }

    @FXML
    void handleBotaoExcluirReceita(ActionEvent event) {
        // Obtém a receita selecionada na ListView
        Receita receitaSelecionada = listViewMinhasReceitas.getSelectionModel().getSelectedItem();

        // Verifica se uma receita foi selecionada
        if (receitaSelecionada != null) {
            controle.excluirReceita(receitaSelecionada);
            carregarMinhasReceitas();
        } else {
            // Exibe uma mensagem de erro se nenhuma receita for selecionada
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma Receita Selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma receita para excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleBotaoVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }
}
