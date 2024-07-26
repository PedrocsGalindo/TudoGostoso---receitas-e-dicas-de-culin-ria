package repositorio;

import controle.Usuario;

import java.io.*;


public class RepositorioUsuario implements IRepositorio<Usuario>{

    private static final File arquivo = new File("src/main/recursos/repositorios/repositorioUsuarios.bin");

    //output = escrever no arquivo
    //input = ler o arquivo

    //Salva o usuario no repositorio, ta tendo um problema de não conseguir salvar mais de um

    public void salvar(Usuario usuario) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){

            objectOutputStream.writeObject(usuario);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //retorna a intancia do Usuario desejado, ainda não ta sendo feito a busca baseada no id.
    public Usuario buscar(int id) {
        Usuario usuario;
        try (FileInputStream FileInputStream = new FileInputStream(arquivo);
             ObjectInputStream objectInputStream = new ObjectInputStream(FileInputStream)) {

             usuario = (Usuario) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            usuario = null;
        }
        return usuario;
    }

}