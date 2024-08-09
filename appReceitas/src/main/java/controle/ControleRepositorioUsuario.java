package controle;

import repositorio.RepositorioUsuario;
import javax.mail.internet.InternetAddress;

//tem que intanciar para poder usar

public class ControleRepositorioUsuario  {

    private final RepositorioUsuario repositorio = new RepositorioUsuario();


    // intancia um Usuario com os parametros dados, Mando pro RepositorioUsuario para salvar e retornar o proprio Usurio
    //  Acho que vai ter que tirar essas intanciação daq, criar um factory
    public Usuario criarESalvarUsuario(String nome, String senha, String email, String cpf){
        Usuario usuario = new Usuario(nome, senha, email,cpf);
        this.repositorio.salvar(usuario);
        return usuario;
    }

    public void excluirUsuario(Usuario usuario){
        this.repositorio.excluir(usuario);
    }

    //retornar o usuario baseado no id
    public Usuario recuperarUsuarioPorId(int id){
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
//Precisa ser adicionado o CPF agora
