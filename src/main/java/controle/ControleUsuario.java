package controle;

import exceptions.ReceitaJaExistenteException;
import exceptions.UsuarioInexistenteException;
import exceptions.UsuarioJaExistenteException;
import modelo.ItemIngrediente;
import modelo.Receita;
import modelo.Usuario;
import modelo.UsuarioChef;
import repositorio.RepositorioUsuario;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class ControleUsuario {

    private final RepositorioUsuario repositorio;
    private final ControleReceita controleReceita;

    public ControleUsuario(RepositorioUsuario repositorio, ControleReceita controleReceita) {
        this.repositorio = repositorio;
        this.controleReceita = controleReceita;
    }

    //repositorio
    public  void salvarUsuario(Usuario usuario) throws UsuarioJaExistenteException {
        if (repositorio.buscarPorEmail(usuario.getEmail()) != null){
            throw new UsuarioJaExistenteException("Já existe uma conta com esse email");
        }
        if (repositorio.buscarPorCpf(usuario.getCpf()) != null){
            throw new UsuarioJaExistenteException("Já existe uma conta com esse CPF");
        }
        repositorio.salvar(usuario);
    }
    public  void excluirUsuario(Usuario usuario){ repositorio.excluir(usuario);}
    public void atualizarUsuario(Usuario usuario) {
        repositorio.update(usuario);
    }
    public int getLastId(){
        return repositorio.getLastId();
    }
    //como a busca retorna um usuario, caso estejamos buscando um UsuarioChef deve ser feito um cast
    public  Usuario recuperarUsuarioPorId(int id){
        return repositorio.buscarPorId(id);
    }
    public Usuario recuperarUsuarioPorCpf(String cpf) throws UsuarioInexistenteException {
        Usuario usuario = repositorio.buscarPorCpf(cpf);
        if (usuario == null){
            throw new UsuarioInexistenteException("Cpf invalido");
        }else{
            return usuario;
        }
    }
    public Usuario recuperarUsuarioPorEmail(InternetAddress email) throws UsuarioInexistenteException{
        Usuario usuario = repositorio.buscarPorEmail(email);
        if (usuario == null){
            throw new UsuarioInexistenteException("Email invalido");
        }else{
            return usuario;
        }
    }

    //Usuario
    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {
        //validações
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();

            if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}

            int id = getLastId() + 1;
            Usuario usuario = new Usuario(nome, senha, emailAddress, cpf, id);
            salvarUsuario(usuario);
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
    public UsuarioChef criarUsuarioChef(Usuario usuario)throws NullPointerException{
        if (usuario == null) {
            throw new NullPointerException();
        }
        UsuarioChef usuarioChef = new UsuarioChef(usuario);
        excluirUsuario(usuario);
        try{
            salvarUsuario(usuarioChef);
        }catch (UsuarioJaExistenteException e){
            System.out.println(e.getMessage());
        }
        return usuarioChef;
    }
    public void addReceitasFav(Usuario usuario, Receita receita) {
        usuario.addReceitasFav(receita);
        atualizarUsuario(usuario);
    }

    //UsuarioChef
    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria) {
        int id = controleReceita.getLastId() + 1;
        Receita receita = new Receita(id, titulo, autor, ingredientes, preparo, modoDePreparo, tempoDePreparo, categoria);
        try {
            controleReceita.cadastrarReceita(receita);
        } catch (ReceitaJaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
