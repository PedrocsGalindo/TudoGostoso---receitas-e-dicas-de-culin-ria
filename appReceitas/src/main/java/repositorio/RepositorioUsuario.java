package repositorio;

import controle.Usuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.InternetAddress;


public class RepositorioUsuario implements IRepositorio<Usuario> {

    //caminho do arquivo, relativo em relação ao content root
    private static final Path path = Paths.get("appReceitas/src/main/recursos/repositorios/repositorioUsuarios.ser");


    //Busca e retorna a lista de Usuarios, se tiver fazia, retorna uma lista vazia
    public List<Usuario> buscar(){

        List<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream( Files.newInputStream(path))){
            try{
                usuarios = (List<Usuario>) input.readObject();
            }catch (ClassNotFoundException e){
                System.out.println("erro ao ler arquivo");
            }
        }catch (IOException e){
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

    //remove usuario existente
    public void excluir(Usuario usuario) {
        List<Usuario> usuarios = buscar();
        int hUsuarios = usuarios.size();
        usuarios.remove(usuario);

        //verefica se a lista foi alterada, se ela for, significa que existe o usuario e foi excluido
        if (usuarios.size() != hUsuarios){
            try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))){
                output.writeObject(usuarios);
            }catch (IOException e){
                System.out.println("Erro ao abrir arquivo para escrever");
            }
        }
    }


    //recuperar Usuario com id, se não achar retorna NULL
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

    //recuperar Usuario com cpf, se não achar retorna NULL
    public Usuario recuperar(String cpf){

        List<Usuario> usuarios = buscar();
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                usuario = u;
            }
        }
        return usuario;
    }
    //recuperar Usuario com emaill, se não achar retorna NULL
    public Usuario recuperar(InternetAddress  email){

        List<Usuario> usuarios = buscar();
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                usuario = u;
            }
        }
        return usuario;
    }
}