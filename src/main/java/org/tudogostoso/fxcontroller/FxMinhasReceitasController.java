package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.tudogostoso.modelo.Sessao;

public class FxMinhasReceitasController {

    private ListView<Receita> listViewMinhasReceitas;

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
        minhasReceitasList.setAll(usuario.getMinhasReceitas());
    }
    
    @FXML
    private Button botaoVoltar;

    @FXML
    private ListView<?> listViewMinhasReceitas;

    @FXML
    private Button botaoAdicionarReceita;

    @FXML
    private Button botaoExcluirReceita;

    FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    void handleBotaoAdicionarReceita(ActionEvent event) {
        Sessao.setUltimaCena("minhasReceitas");
        gerenciadorTelas.mudarTela("criarReceitas", event);
    }

    @FXML
    void handleBotaoExcluirReceita(ActionEvent event) {

    }

    @FXML
    void handleBotaoVoltar(ActionEvent event) {
        gerenciadorTelas.mudarTela("perfil", event);
    }
}
