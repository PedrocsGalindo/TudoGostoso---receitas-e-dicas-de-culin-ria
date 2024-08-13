package controle;

import exceptions.UsuarioJaExistenteException;
import modelo.Usuario;
import modelo.UsuarioChef;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ControleUsuario {

    private final ControleRepositorioUsuario controleRepositorioUsuario;

    public ControleUsuario(ControleRepositorioUsuario controleRepositorioU) {
        this.controleRepositorioUsuario = controleRepositorioU;
    }

    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {
        //validações
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();

            if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}

            int id = controleRepositorioUsuario.getLastId();
            Usuario usuario = new Usuario(nome, senha, emailAddress, cpf, id);
            controleRepositorioUsuario.salvarUsuario(usuario);
            return usuario;
        } catch (AddressException e) {
            System.out.println("email invalido");
        } catch (NullPointerException e){
            System.out.println("algum dos campos esta vazio");
        } catch (UsuarioJaExistenteException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Usuario criarUsuarioChef(Usuario usuario)throws NullPointerException{
        if (usuario == null) {
            throw new NullPointerException();
        }
        Usuario usuarioChef = new UsuarioChef(usuario);
        controleRepositorioUsuario.excluirUsuario(usuario);
        try{
            controleRepositorioUsuario.salvarUsuario(usuarioChef);
        }catch (UsuarioJaExistenteException e){
            System.out.println(e.getMessage());
        }
        return usuarioChef;
    }
}
