package org.tudogostoso.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tudogostoso.controle.Controle;
import org.tudogostoso.controle.ControleFactory;
import org.tudogostoso.exceptions.ObjetoJaExiste;
import org.tudogostoso.exceptions.ReceitaJaExistenteException;
import org.tudogostoso.exceptions.UsuarioJaExistenteException;
import org.tudogostoso.modelo.*;

import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;

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
        Controle controle = ControleFactory.criarControleGeral();

        try {
            Usuario pedro = controle.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "31321");
        }catch (AddressException e) {
            System.out.println("email invalido");
        } catch (NullPointerException e){
            System.out.println("algum dos campos esta vazio");
        } catch (UsuarioJaExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            Usuario caio = controle.criarUsuario("caio", "4315","pedrocgsouza4fc@gmail", "123141");
        }catch (AddressException e) {
            System.out.println("email invalido");
        } catch (NullPointerException e){
            System.out.println("algum dos campos esta vazio");
        } catch (UsuarioJaExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            Usuario joaquim = controle.criarUsuario("Joaquim", "senha", "cesarpedrog23@gmail","3214");
        }catch (AddressException e) {
            System.out.println("email invalido");
        } catch (NullPointerException e){
            System.out.println("algum dos campos esta vazio");
        } catch (UsuarioJaExistenteException e) {
            System.out.println(e.getMessage());
        }

        Usuario caioc = controle.recuperarUsuarioPorId(2);
        Usuario pedror= controle.recuperarUsuarioPorId(1);
        Usuario joaquimm = controle.recuperarUsuarioPorId(3);

        try {
            controle.criarIngrediente("batata");
        } catch (ObjetoJaExiste e){
            System.out.println(e.getMessage());
        }
        try {
            controle.criarIngrediente("cenoura");
        } catch (ObjetoJaExiste e){
            System.out.println(e.getMessage());
        }

        Ingrediente cenoura = controle.buscarIngredientePorNome("cenoura");
        Ingrediente batata = controle.buscarIngredientePorNome("batata");

        ItemIngrediente itemCenoura = controle.criarItemIngrediente(cenoura, 200, UnidadeMedida.GRAMAS);
        ItemIngrediente itemBatata = controle.criarItemIngrediente(batata, 300, UnidadeMedida.GRAMAS);
        List<ItemIngrediente> ingredientes = List.of(itemCenoura, itemBatata);

        UsuarioChef joaquimChef = controle.criarUsuarioChef(joaquimm);

        List<String> preparo = List.of("bater", "juntar tudo");
        try{
            controle.criarReceita("bolo", joaquimChef, ingredientes, preparo, "2min", "larica");
        } catch (ReceitaJaExistenteException e){
            System.out.println(e.getMessage());
        }



        launch();
    }
}