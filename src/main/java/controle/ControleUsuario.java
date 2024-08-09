package controle;

import exceptions.UsuarioJaExistenteException;
import modelo.Usuario;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ControleUsuario {

    private final ControleRepositorioUsuario controleRepositorioU;

    public ControleUsuario(ControleRepositorioUsuario controleRepositorioU) {
        this.controleRepositorioU = controleRepositorioU;
    }

    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {
        //validações
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();

            if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}


            Usuario usuario = new Usuario(nome, senha, emailAddress,cpf);
            controleRepositorioU.salvarUsuario(usuario);
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



}
