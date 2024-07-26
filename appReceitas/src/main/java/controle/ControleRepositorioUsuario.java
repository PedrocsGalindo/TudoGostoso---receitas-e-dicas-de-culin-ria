package controle;

import repositorio.RepositorioUsuario;

public class ControleRepositorioUsuario  {

    private final RepositorioUsuario repositorio = new RepositorioUsuario();
    public Usuario criarESalvarUsuario(String nome, String senha, String email, String cpf){
        Usuario usuario = new Usuario(nome, senha, email,cpf);
        this.repositorio.salvar(usuario);
        return usuario;
    }

    public Usuario recuperarUsuarioPorId(int id){
        Usuario usuario = repositorio.buscar(id);
        return usuario;
    }
}
//Precisa ser adicionado o CPF agora
