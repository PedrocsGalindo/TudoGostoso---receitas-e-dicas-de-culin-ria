package org.tudogostoso.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.modelo.*;

import java.io.IOException;
import java.util.List;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/org/tudogostoso/telas/buscar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Busca");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Controle controle = ControleFactory.criarControleGeral();

        Usuario pedro = controle.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "31321");
        Usuario caio = controle.criarUsuario("caio", "4315","pedrocgsouza4fc@gmail", "123141");
        Usuario joaquim = controle.criarUsuario("Joaquim", "senha", "cesarpedrog23@gmail","3214");

        Usuario caioc = controle.recuperarUsuarioPorId(2);
        Usuario pedror= controle.recuperarUsuarioPorId(1);
        Usuario joaquimm = controle.recuperarUsuarioPorId(3);

        controle.criarIngrediente("cenoura");
        controle.criarIngrediente("batata");

        controle.excluirUsuario(caioc);
        Usuario caioteste = controle.recuperarUsuarioPorId(2);
        System.out.println(caioteste.getEmail());


        launch();
    }
}