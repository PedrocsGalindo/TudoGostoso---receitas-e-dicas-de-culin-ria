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

    private Stage stage;
    private Scene scene;
    private Map<String, Parent> telasCarregadas = new HashMap<>();

    public FxGerenciadorTelas(){
        carregarTelas("Login","/org/tudogostoso/telas/login.fxml");
    }
//Método para carregar as telas e armazenar no map
    private void carregarTelas(String nomeTela, String caminhoFXML){
        try {
            Parent tela = FXMLLoader.load(getClass().getResource(caminhoFXML));
            telasCarregadas.put(nomeTela,tela);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Método para mudar de tela usando o map
    public void mudarTela(String nomeTela, Event evento){
        if(telasCarregadas.containsKey(nomeTela)) {
            Parent tela = telasCarregadas.get(nomeTela);
            stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            scene = new Scene(tela);
            stage.setScene(scene);
            stage.setTitle(nomeTela);
            stage.show();
        }else {
            System.out.println("Tela não foi encontrada"+ nomeTela);
        }
    }


}

