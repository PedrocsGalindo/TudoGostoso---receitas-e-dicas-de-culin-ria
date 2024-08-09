package controle;

import modelo.Usuario;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ControleUsuario {

    public static Usuario criarUsuario(String nome, String senha, String email, String cpf) throws NumberFormatException {
        //validações
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();

            if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}


            Usuario usuario = new Usuario(nome, senha, emailAddress,cpf);
            ControleRepositorioUsuario.salvarUsuario(usuario);
            return usuario;
        } catch (AddressException e) {
            System.out.println("email invalido");
        } catch (NullPointerException e){
            System.out.println("algum dos campos esta vazio");
        }
        return null;
    }
}
