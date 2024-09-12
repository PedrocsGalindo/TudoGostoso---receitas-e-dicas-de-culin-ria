package org.tudogostoso.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//cpf 12345678910
//senha 123
//email: pedro@gmail.com
//cpf 12345678911
//senha 123
//email: vinicius@gmail.com

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/org/tudogostoso/telas/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 610);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}