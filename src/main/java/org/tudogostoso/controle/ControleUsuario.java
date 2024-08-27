package org.tudogostoso.controle;

import org.tudogostoso.exceptions.ReceitaJaExistenteException;
import org.tudogostoso.exceptions.UsuarioInexistenteException;
import org.tudogostoso.exceptions.UsuarioJaExistenteException;
import org.tudogostoso.modelo.*;
import org.tudogostoso.repositorios.RepositorioUsuarios;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class ControleUsuario {

    private final RepositorioUsuarios repositorio;
    private final ControleReceita controleReceita;
    private final ControleAvaliacao controleAvaliacao;

    public ControleUsuario(RepositorioUsuarios repositorio, ControleReceita controleReceita, ControleAvaliacao controleAvaliacao) {
        this.repositorio = repositorio;
        this.controleReceita = controleReceita;
        this.controleAvaliacao = controleAvaliacao;
    }

    //repositorio
    public  void salvarUsuario(Usuario usuario) throws UsuarioJaExistenteException {
        if (repositorio.buscarPorEmail(usuario.getEmail()) != null){
            if (usuario.getClass() != UsuarioChef.class){
                throw new UsuarioJaExistenteException("Já existe uma conta com esse email");
            }
        }
        if (repositorio.buscarPorCpf(usuario.getCpf()) != null){
            if (usuario.getClass() != UsuarioChef.class){
                throw new UsuarioJaExistenteException("Já existe uma conta com esse CPF");
            }
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
    public void criarAvalizacao(int nota, String comentario, Usuario usuario, Receita receita){
        if (!comentario.isEmpty()){
            int id = controleAvaliacao.getLastId() + 1;
            Avaliacao avaliacao = new Avaliacao(nota, comentario, usuario, receita, id);

            UsuarioChef autor = (UsuarioChef) receita.getAutor();
            receita.adicioarAvaliacao(avaliacao);
            List<Receita> receitas = autor.getMinhasReceitas();
            int indice = receitas.indexOf(receita);
            if (indice != -1){
                receitas.set(indice, receita);
            }
            controleReceita.atualizarReceita(receita);
            controleAvaliacao.salvar(avaliacao);
            atualizarUsuario(autor);
        }

    }

    //UsuarioChef
    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String tempoDePreparo, String categoria) {
        int id = controleReceita.getLastId() + 1;
        Receita receita = new Receita(id, titulo, autor, ingredientes, preparo, tempoDePreparo, categoria);
        try {
            cadastrarReceita(receita);
            autor.addMinhasReceita(receita);
            atualizarUsuario(autor);
        } catch (ReceitaJaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }
    public void cadastrarReceita(Receita receita) throws ReceitaJaExistenteException{
        if(controleReceita.buscarReceitaPorAutorETitulo(receita.getAutor(), receita.getTitulo()) == null) {
            controleReceita.salvarReceita(receita);
        }else {
            throw new ReceitaJaExistenteException("Usuario "+ receita.getAutor().getNome() + " já possui receita com o titulo " + receita.getTitulo());
        }
    }
}

