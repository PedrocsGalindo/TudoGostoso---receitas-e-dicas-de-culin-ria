package org.tudogostoso.fxcontroller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FxGerenciadorTelas {

    private static FxGerenciadorTelas instancia = new FxGerenciadorTelas();
    private Stage stage;
    private Scene scene;
    private Map<String, Parent> telasCarregadas = new HashMap<>();
    private Map<String, String> titulosDasTelas = new HashMap<>(); // Adicionando um Map para armazenar os títulos

    // Fazer semelhante ao primeiro para carregar as outras telas também
    private FxGerenciadorTelas() {
        carregarTelas("Login", "/org/tudogostoso/telas/login.fxml", "Login");
        carregarTelas("perfil", "/org/tudogostoso/telas/perfil.fxml", "Perfil do Usuário");
        carregarTelas("feed", "/org/tudogostoso/telas/feed.fxml", "Feed de Receitas");
        carregarTelas("cadastro","org/tudogostoso/telas/cadastro.fxml","Cadastro de Receitas");
        carregarTelas("criarReceitas","org/tudogostoso/telas/criarReceitas.fxml","Criar Receitas");
        carregarTelas("buscar","org/tudogostoso/telas/buscar.fxml","Busca");
        carregarTelas("receita","org/tudogostoso/telas/receita","Receita");
        carregarTelas("perfilCozinheiro","org/tudogostoso/telas/perfilCozinheiro","Perfil Cozinheiro");
        // Adicionar mais telas aqui conforme o necessário
    }

    // Método para carregar as telas e armazenar no map
    private void carregarTelas(String nomeTela, String caminhoFXML, String tituloTela) {
        try {
            Parent tela = FXMLLoader.load(getClass().getResource(caminhoFXML));
            telasCarregadas.put(nomeTela, tela);
            titulosDasTelas.put(nomeTela, tituloTela); // Armazenando o título da tela
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mudar de tela usando o map
    public void mudarTela(String nomeTela, Event evento) {
        if (telasCarregadas.containsKey(nomeTela)) {
            Parent tela = telasCarregadas.get(nomeTela);
            stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            scene = new Scene(tela);
            stage.setScene(scene);
            stage.setTitle(titulosDasTelas.getOrDefault(nomeTela, nomeTela)); // Definindo o título da tela
            stage.show();
        } else {
            System.out.println("Tela não foi encontrada: " + nomeTela); // Melhorando a mensagem de erro
        }

    }
    // Método para ser singleton, pode ser mudado para outro, caso necessário
    public static FxGerenciadorTelas getInstance() {
        return instancia;
    }

}
