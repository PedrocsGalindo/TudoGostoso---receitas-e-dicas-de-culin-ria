package repositorio;

import controle.ControleRepositorioUsuario;

import controle.Usuario;

public class Main {
    public static void main(String[] args) {

        ControleRepositorioUsuario controle = new ControleRepositorioUsuario();
        Usuario pedro = controle.criarESalvarUsuario("pedro","1231","pedrocgsouza23@gmail.com","1231414141");

        Usuario caio = controle.criarESalvarUsuario("caio","1231","pedrocgsouza23@gmail.com","1231414141");




    }
}