package controle;

import exceptions.UsuarioJaExistenteException;
import modelo.Usuario;
import repositorio.RepositorioUsuario;
import javax.mail.internet.InternetAddress;

public class ControleRepositorioUsuario {

    private final RepositorioUsuario repositorio;

    public ControleRepositorioUsuario(RepositorioUsuario repositorio){
        this.repositorio = repositorio;

    }

    public  void salvarUsuario(Usuario usuario) throws UsuarioJaExistenteException {
        if (repositorio.buscarPorEmail(usuario.getEmail()) != null){
            throw new UsuarioJaExistenteException("Já existe uma conta com esse email");
        }
        if (repositorio.buscarPorCpf(usuario.getCpf()) != null){
            throw new UsuarioJaExistenteException("Já existe uma conta com esse CPF");
        }
        repositorio.salvar(usuario);
    }

    public  void excluirUsuario(Usuario usuario){ repositorio.excluir(usuario);
    }

    //retornar o usuario baseado no id
    public  Usuario recuperarUsuarioPorId(int id){
        return repositorio.buscarPorId(id);
    }

    //retornar o usuario baseado no cpf
    public Usuario recuperarUsuarioPorCpf(String cpf){
        return repositorio.buscarPorCpf(cpf);
    }

    //retornar o usuario baseado no email
    public Usuario recuperarUsuarioPorEmail(InternetAddress email){
        return repositorio.buscarPorEmail(email);
    }
}

