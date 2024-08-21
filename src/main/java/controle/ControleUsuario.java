package controle;

import exceptions.ReceitaJaExistenteException;
import exceptions.UsuarioJaExistenteException;
import modelo.ItemIngrediente;
import modelo.Receita;
import modelo.Usuario;
import modelo.UsuarioChef;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class ControleUsuario {

    private final ControleRepositorioUsuario controleRepositorioUsuario;
    private final ControleReceita controleReceita;

    public ControleUsuario(ControleRepositorioUsuario controleRepositorioU, ControleReceita controleReceita) {
        this.controleRepositorioUsuario = controleRepositorioU;
        this.controleReceita = controleReceita;
    }

    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {
        //validações
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();

            if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}

            int id = controleRepositorioUsuario.getLastId() + 1;
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

    public void addReceitasFav(Usuario usuario, Receita receita) {
        usuario.addReceitasFav(receita);
        controleRepositorioUsuario.atualizarUsuario(usuario);
    }
    //UsuarioChef
    public void criarReceita(String titulo, Usuario autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria) {
        int id = controleReceita.getLastId() + 1;
        Receita receita = new Receita(id, titulo, (UsuarioChef) autor, ingredientes, preparo, modoDePreparo, tempoDePreparo, categoria);
        try {
            controleReceita.cadastrarReceita(receita);
        } catch (ReceitaJaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
