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
    private Map<String, String> caminhosDasTelas = new HashMap<>();
    private Map<String, String> titulosDasTelas = new HashMap<>();

    // Inicializando e carregando as telas na inicialização, para adicionar as telas seguintes, deve ser seguido os exemplos
    private FxGerenciadorTelas() {
        carregarTelas("Login", "/org/tudogostoso/telas/login.fxml", "Login");
        carregarTelas("perfil", "/org/tudogostoso/telas/perfil.fxml", "Perfil do Usuário");
        carregarTelas("feed", "/org/tudogostoso/telas/feed.fxml", "Feed de Receitas");
        carregarTelas("cadastro", "/org/tudogostoso/telas/cadastro.fxml", "Cadastro de Receitas");
        carregarTelas("criarReceitas", "/org/tudogostoso/telas/criarReceitas.fxml", "Criar Receitas");
        carregarTelas("buscar", "/org/tudogostoso/telas/buscar.fxml", "Busca");
        carregarTelas("receita", "/org/tudogostoso/telas/receita.fxml", "Receita");
        carregarTelas("receitasFavoritas","/org/tudogostoso/telas/receitasFavoritas.fxml","Receitas Favoritas");
        carregarTelas("listadecompras", "/org/tudogostoso/telas/listadecompras.fxml", "Lista de Compras");
        carregarTelas("minhasReceitas","/org/tudogostoso/telas/minhasReceitas.fxml", "Minhas Receitas");
        carregarTelas("listaDeComprasV","/org/tudogostoso/telas/listaDeComprasV.fxml", "v");

        // Adicionar mais telas conforme necessário
    }

    // Método para carregar as telas e armazenar os caminhos no mapa, foi add para pode voltar
    private void carregarTelas(String nomeTela, String caminhoFXML, String tituloTela) {
        caminhosDasTelas.put(nomeTela, caminhoFXML);
        titulosDasTelas.put(nomeTela, tituloTela);
    }

    // Método para mudar de tela utilizando o mapa
    public void mudarTela(String nomeTela, Event evento) {
        if (caminhosDasTelas.containsKey(nomeTela)) {
            try {
                String caminhoFXML = caminhosDasTelas.get(nomeTela);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
                Parent tela = loader.load(); // Carregar uma nova instância de Parent
                stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                Scene novaCena = new Scene(tela);
                stage.setScene(novaCena);
                stage.setTitle(titulosDasTelas.getOrDefault(nomeTela, nomeTela)); // Definindo o título da tela
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tela não foi encontrada: " + nomeTela);// talvez possa ser mudado essa mensagem de erro
        }
    }

    // Método para ser singleton, pode ser modificado se necessário
    public static FxGerenciadorTelas getInstance() {
        return instancia;
    }
}
