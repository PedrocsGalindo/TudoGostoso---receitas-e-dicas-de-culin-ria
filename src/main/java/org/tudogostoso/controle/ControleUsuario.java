package org.tudogostoso.controle;

import org.tudogostoso.exceptions.ObjetoJaExiste;
import org.tudogostoso.exceptions.ReceitaJaExistenteException;
import org.tudogostoso.exceptions.UsuarioInexistenteException;
import org.tudogostoso.exceptions.UsuarioJaExistenteException;
import org.tudogostoso.modelo.*;
import org.tudogostoso.repositorios.IRepositorioUsuarios;
import org.tudogostoso.repositorios.RepositorioImagens;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ControleUsuario {

    private final IRepositorioUsuarios repositorio;
    private final ControleReceita controleReceita;
    private final ControleAvaliacao controleAvaliacao;
    private final ControleIngrediente controleIngrediente;
    private final RepositorioImagens repositorioImagens;

    public ControleUsuario(IRepositorioUsuarios repositorio, ControleReceita controleReceita, ControleAvaliacao controleAvaliacao, ControleIngrediente controleIngrediente, RepositorioImagens repositorioImagens) {
        this.repositorio = repositorio;
        this.controleReceita = controleReceita;
        this.controleAvaliacao = controleAvaliacao;
        this.controleIngrediente = controleIngrediente;
        this.repositorioImagens = repositorioImagens;
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

    //Usuario, tem que tratar as exceções
    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) throws AddressException, NullPointerException, UsuarioJaExistenteException{
        //validações

        InternetAddress emailAddress = new InternetAddress(email);
        emailAddress.validate();

        if (nome.isEmpty() || senha.isEmpty()|| email.isEmpty() || cpf.isEmpty()) {throw new NullPointerException();}

        int id = getLastId() + 1;
        Usuario usuario = new Usuario(nome, senha, emailAddress, cpf, id);
        salvarUsuario(usuario);
        return usuario;
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
    public void criarAvalizacao(int nota, String comentario, Usuario usuario, Receita receita) throws NullPointerException {
        if (!comentario.isEmpty()){
            if (usuario != null){
                int id = controleAvaliacao.getLastId() + 1;
                Avaliacao avaliacao = new Avaliacao(nota, comentario, usuario, receita, id);

                UsuarioChef autor = (UsuarioChef) receita.getAutor();
                receita.adicioarAvaliacao(avaliacao);
                List<Receita> receitas = autor.getMinhasReceitas();
                int indice = receitas.indexOf(receita);
                if (indice != -1) {
                    receitas.set(indice, receita);
                }
                controleReceita.atualizarReceita(receita);
                controleAvaliacao.salvar(avaliacao);
                atualizarUsuario(autor);
            }else {
                    throw new NullPointerException("Usuario é null");
                }
        } else {
            throw new NullPointerException("comentario esta vazio");
        }

    }
    public Ingrediente criarIngrediente(String nome) throws ObjetoJaExiste, NullPointerException {
        if (nome == null){
            throw new NullPointerException();
        }
        Ingrediente ingrediente = controleIngrediente.buscarIngredientePorNome(nome);
        if(ingrediente == null){
            int id = controleIngrediente.getLastId();
            ingrediente = new Ingrediente(nome, id);
            controleIngrediente.salvarIngrediente(ingrediente);
        } else {
            throw new ObjetoJaExiste("Ingrediente já existe");
        }
        return ingrediente;
    }
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida) throws NullPointerException{
        if(ingrediente == null || quantidade == 0 || medida == null){
            throw new NullPointerException("Preencha todos os campos");
        } else {
            return new ItemIngrediente(ingrediente, quantidade, medida);
        }
    }
    //UsuarioChef
    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String tempoDePreparo, String categoria) throws ReceitaJaExistenteException {
        int id = controleReceita.getLastId() + 1;
        if (titulo.isEmpty() || autor == null || ingredientes.isEmpty() || preparo.isEmpty() || tempoDePreparo.isEmpty() || categoria.isEmpty()){
            throw new NullPointerException("Campo vazio");
        }
        Receita receita = new Receita(id, titulo, autor, ingredientes, preparo, tempoDePreparo, categoria);
        cadastrarReceita(receita);
        autor.addMinhasReceita(receita);
        atualizarUsuario(autor);
    }
    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String tempoDePreparo, String categoria, String caminhoImagem) throws ReceitaJaExistenteException {
        int id = controleReceita.getLastId() + 1;
        if (titulo.isEmpty() || autor == null || ingredientes.isEmpty() || preparo.isEmpty() || tempoDePreparo.isEmpty() || categoria.isEmpty()){
            throw new NullPointerException("Campo vazio");
        }
        Receita receita = new Receita(id, titulo, autor, ingredientes, preparo, tempoDePreparo, categoria, caminhoImagem);
        cadastrarReceita(receita);
        autor.addMinhasReceita(receita);
        atualizarUsuario(autor);
    }
    public String salvarImagem(File arquivo, String nomeArquivo) throws IOException {

       return repositorioImagens.salvar(arquivo,nomeArquivo);
    }
    public void cadastrarReceita(Receita receita) throws ReceitaJaExistenteException{
        if(controleReceita.buscarReceitaPorAutorETitulo(receita.getAutor(), receita.getTitulo()) == null) {
            controleReceita.salvarReceita(receita);
        }else {
            throw new ReceitaJaExistenteException("Usuario "+ receita.getAutor().getNome() + " já possui receita com o titulo " + receita.getTitulo());
        }
    }
}

