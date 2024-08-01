package repositorio;

import controle.Usuario;

import java.util.List;
import javax.mail.internet.InternetAddress;


public class RepositorioUsuario extends RepositorioGenerico<Usuario> {

    // Define o caminho do arquivo específico para usuários
    public RepositorioUsuario() {
        super("appReceitas/out/production/appReceitas/repositorios/RepositorioUsuarios/repositorio.ser");
    }

    //buscar, salvar e excluir herdados de Generico

    //recuperar Usuario com id, se não achar retorna NULL
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
    public Usuario buscarPorEmail(InternetAddress  email){

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