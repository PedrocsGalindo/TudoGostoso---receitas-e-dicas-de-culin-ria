package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Usuario;

import java.util.List;
import java.util.NoSuchElementException;
import javax.mail.internet.InternetAddress;

public class RepositorioUsuarios extends RepositorioGenerico<Usuario> implements IRepositorioUsuarios {

    // Define o caminho do arquivo específico para usuários
    public RepositorioUsuarios() {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioUsuarios/repositorio.ser");
    }

    //buscar, salvar e excluir herdados de Generico

    @Override
    public int getLastId(){
        int id;
        List<Usuario> usuarios = buscar();
        try {
            id = usuarios.get(usuarios.size() - 1).getId();
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            id = 0;
        }
        return id;
    }

    //recuperar Usuario com id, se não achar retorna NULL
    @Override
    public Usuario buscarPorId(int id) {

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
    @Override
    public Usuario buscarPorCpf(String cpf){

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
    @Override
    public Usuario buscarPorEmail(InternetAddress email){

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