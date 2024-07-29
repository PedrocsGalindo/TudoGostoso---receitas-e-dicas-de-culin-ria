package repositorio;

import controle.Usuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class RepositorioUsuario implements IRepositorio<Usuario> {

    //caminho do arquivo, relativo em relação ao content root
    private static final Path path = Paths.get("appReceitas/src/main/recursos/repositorios/repositorioUsuarios.ser");


    //Busca e retorna a lista de Usuarios
    public List<Usuario> buscar(){

        List<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream( Files.newInputStream(path))){
            try{
                usuarios = (List<Usuario>) input.readObject();
            }catch (ClassNotFoundException e){
                System.out.println("erro ao ler arquivo");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro ao abrir arquivo");
        }
        return usuarios;
    }

    //salva usurio novo
    public void salvar(Usuario usuario) {

        List<Usuario> usuarios = buscar();
        usuarios.add(usuario);
        try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))){
            output.writeObject(usuarios);
        }catch (IOException e){
            System.out.println("Erro ao abrir arquivo para escrever");
        }
    }


    //recuperar Usuario com id
    public Usuario recuperar(int id) {

        List<Usuario> usuarios = buscar();
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                usuario = u;
            }
        }
        return usuario;
    }

}