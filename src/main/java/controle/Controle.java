package controle;

import modelo.Usuario;

import javax.mail.internet.InternetAddress;

public class Controle {
    private final ControleRepositorioUsuario controleRepositorioUsuario;
    private final ControleRepositorioReceita controleRepositorioReceita;
    private final ControleUsuario controleioUsuario;

    public Controle(ControleRepositorioUsuario controleRepositorioUsuario, ControleRepositorioReceita controleRepositorioReceita, ControleUsuario controleioUsuario) {
        this.controleRepositorioUsuario = controleRepositorioUsuario;
        this.controleRepositorioReceita = controleRepositorioReceita;
        this.controleioUsuario = controleioUsuario;
    }

    //metodos de ControleRepositorioUsuario

    public void salvarUsuario(Usuario usuario) throws Exception {
        this.controleRepositorioUsuario.salvarUsuario(usuario);
    }
    public  void excluirUsuario(Usuario usuario){ this.controleRepositorioUsuario.excluirUsuario(usuario);
    }
    public  Usuario recuperarUsuarioPorId(int id){
        return this.controleRepositorioUsuario.recuperarUsuarioPorId(id);
    }
    public Usuario recuperarUsuarioPorCpf(String cpf){
        return this.controleRepositorioUsuario.recuperarUsuarioPorCpf(cpf);
    }
    public Usuario recuperarUsuarioPorEmail(InternetAddress email){
        return this.controleRepositorioUsuario.recuperarUsuarioPorEmail(email);
    }

    //metodos de ControleRepositorioReceita

    //metoos de ControleUsuario
    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {
        return this.controleioUsuario.criarUsuario(nome, senha, email, cpf);
    }
}
