package controle;

import repositorio.RepositorioUsuario;

public class ControleRepositorioUsuario {

    private final RepositorioUsuario repositorio = new RepositorioUsuario();
    public Usuario criarESalvarUsuario(String nome, String senha, String email, String cpf){
        Usuario usuario = new Usuario(nome, senha, email,cpf);
        this.repositorio.salvarUsuario(usuario);
        return usuario;
    }

    public Usuario recuperarUsuarioPorId(int id){
        Usuario usuario = repositorio.buscaERecuperarUsuarioPorId(id);
        return usuario;
    }
}
//Precisa ser adicionado o CPF agora
