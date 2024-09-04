package org.tudogostoso.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Sessao;

import java.util.List;

public class FxFeedController {

    @FXML
    private GridPane gridBTRPerfil, gridBTNBuscarReceitas;


    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelReceitas;

    @FXML
    private VBox feed;
    @FXML
    private Button BTRPerfil;
    @FXML
    private Button BTNBuscarReceitas;


    private final Controle controle = ControleFactory.criarControleGeral();

    private  final FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();

    @FXML
    public void initialize() {
        List<Receita> receitas = controle.buscarReceitasAleatorias();

        for (Receita receita : receitas) {
            HBox receitaBox = new HBox();
            receitaBox.setSpacing(10);

            VBox detalhesReceita = new VBox();
            detalhesReceita.setSpacing(5);

            Label titulo = new Label("Título: " + receita.getTitulo());
            Label autor = new Label("Autor: " + receita.getAutor().getNome());
            Label tempoPreparo = new Label("Tempo de Preparo: " + receita.getTempoDePreparo());
            Label categoria = new Label("Categoria: " + receita.getCategoria());

            Button btnVerMais = new Button("Ver Mais");
            btnVerMais.setOnAction(event -> mostrarMaisInformacoes(event, receita));

            detalhesReceita.getChildren().addAll(titulo, autor, tempoPreparo, categoria);
            receitaBox.getChildren().addAll(detalhesReceita, btnVerMais);

            feed.getChildren().add(receitaBox);
        }
    }

    private void mostrarMaisInformacoes(ActionEvent event, Receita receita) {
        Sessao.setReceitaSessao(receita);
        Sessao.setUltimaCena("feed");
        gerenciadorTelas.mudarTela("receita", event);
    }


    @FXML
    void meuPerfil(ActionEvent event) {

        gerenciadorTelas.mudarTela("perfil",event);
    }
    @FXML
    void mousePorCimaBotaoBusca(MouseEvent event) {
        gridBTNBuscarReceitas.setStyle("-fx-background-color:  #5aa55a;");    }

    @FXML
    void mousePorCimaBotaoPerdil(MouseEvent event) {
        gridBTRPerfil.setStyle("-fx-background-color:  #5aa55a;");
    }

    @FXML
    void mousePorForaBotaoBusca(MouseEvent event) {
        gridBTNBuscarReceitas.setStyle("-fx-background-color:   #90ee90;");

    }

    @FXML
    void mousePorForaBotaoPerdil(MouseEvent event) {
        gridBTRPerfil.setStyle("-fx-background-color:   #90ee90;");
    }
    @FXML
    void buscarReceitas(ActionEvent event){
        gerenciadorTelas.mudarTela("buscar",event);
    }


}
