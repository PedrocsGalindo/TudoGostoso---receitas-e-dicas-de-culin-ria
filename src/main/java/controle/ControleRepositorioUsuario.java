package controle;

import exceptions.UsuarioInexistenteException;
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

    public void atualizarUsuario(Usuario usuario) {
        repositorio.update(usuario);
    }

    public int getLastId(){
        return repositorio.getLastId();
    }

    //retornar o usuario baseado no id
    public  Usuario recuperarUsuarioPorId(int id){
        return repositorio.buscarPorId(id);
    }

    //retornar o usuario baseado no cpf
    public Usuario recuperarUsuarioPorCpf(String cpf) throws UsuarioInexistenteException{
        Usuario usuario = repositorio.buscarPorCpf(cpf);
        if (usuario == null){
            throw new UsuarioInexistenteException("Cpf invalido");
        }else{
            return usuario;
        }
    }

    //retornar o usuario baseado no email
    public Usuario recuperarUsuarioPorEmail(InternetAddress email) throws UsuarioInexistenteException{
        Usuario usuario = repositorio.buscarPorEmail(email);
        if (usuario == null){
            throw new UsuarioInexistenteException("Email invalido");
        }else{
            return usuario;
        }
    }
}

