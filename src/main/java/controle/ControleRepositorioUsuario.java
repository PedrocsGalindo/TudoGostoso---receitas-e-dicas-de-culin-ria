package controle;

import modelo.Usuario;
import repositorio.RepositorioUsuario;
import javax.mail.internet.InternetAddress;

public class ControleRepositorioUsuario  {

    private static final RepositorioUsuario repositorio = new RepositorioUsuario();

    public static void salvarUsuario(Usuario usuario){
        repositorio.salvar(usuario);
    }

    public static void excluirUsuario(Usuario usuario){ repositorio.excluir(usuario);
    }

    //retornar o usuario baseado no id
    public static Usuario recuperarUsuarioPorId(int id){
        return repositorio.buscarPorId(id);
    }

    //retornar o usuario baseado no cpf
    public static Usuario recuperarUsuarioPorCpf(String cpf){
        return repositorio.buscarPorCpf(cpf);
    }

    //retornar o usuario baseado no email
    public static Usuario recuperarUsuarioPorEmail(InternetAddress email){
        return repositorio.buscarPorEmail(email);
    }
}
//Precisa ser adicionado o CPF agora
