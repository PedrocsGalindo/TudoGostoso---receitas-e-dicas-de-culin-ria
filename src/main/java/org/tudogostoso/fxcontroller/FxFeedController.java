package org.tudogostoso.fxcontroller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.tudogostoso.controle.ControleReceita;
import org.tudogostoso.modelo.Receita;

import java.time.format.DateTimeFormatter;
import java.util.List;
public class FxFeedController {
    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelReceitas;

    @FXML
    private ListView<HBox> feedReceitas;

    @FXML
    private Button btnPerfil;

    private final ControleReceita controleReceita;

    // Modificação: Remover o construtor padrão que cria uma nova instância de ControleReceita


    public FxFeedController(ControleReceita controleReceita) {
        this.controleReceita = controleReceita;
    }

    @FXML
    public void initialize() {
        List<Receita> receitas = controleReceita.buscarReceitasAleatorias();

        for (Receita receita : receitas) {
            HBox receitaBox = new HBox();
            receitaBox.setSpacing(10);

            VBox detalhesReceita = new VBox();
            detalhesReceita.setSpacing(5);

            Label titulo = new Label("Título: " + receita.getTitulo());
            Label autor = new Label("Autor: " + receita.getAutor().getNome());
            Label horario = new Label("Horário: " + receita.getHorario().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            Label tempoPreparo = new Label("Tempo de Preparo: " + receita.getTempoDePreparo());
            Label categoria = new Label("Categoria: " + receita.getCategoria());

            Button btnVerMais = new Button("Ver Mais");
            btnVerMais.setOnAction(event -> {

            });

            detalhesReceita.getChildren().addAll(titulo, autor, horario, tempoPreparo, categoria, btnVerMais);
            receitaBox.getChildren().add(detalhesReceita);

            feedReceitas.getItems().add(receitaBox);
        }
    }


}
