package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.tudogostoso.modelo.Sessao;

public class FxMinhasReceitasController {

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
