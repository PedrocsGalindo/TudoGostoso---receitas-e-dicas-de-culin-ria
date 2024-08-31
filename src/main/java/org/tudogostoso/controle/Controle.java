package org.tudogostoso.controle;

import org.tudogostoso.exceptions.ObjetoJaExiste;
import org.tudogostoso.exceptions.ReceitaJaExistenteException;
import org.tudogostoso.exceptions.UsuarioInexistenteException;
import org.tudogostoso.exceptions.UsuarioJaExistenteException;
import org.tudogostoso.modelo.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controle {
    private final ControleUsuario controleUsuario;
    private final ControleReceita controleReceita;
    private final ControleIngrediente controleIngrediente;
    private final ControleAvaliacao controleAvaliacao;
    private final  ControleUnidadeDeMedida controleUnidadeDeMedida;

    public Controle(ControleUsuario controleUsuario, ControleReceita controleReceita, ControleIngrediente controleIngrediente, ControleAvaliacao controleAvaliacao, ControleUnidadeDeMedida controleUnidadeDeMedida) {
        this.controleUsuario = controleUsuario;
        this.controleReceita = controleReceita;
        this.controleIngrediente = controleIngrediente;
        this.controleAvaliacao = controleAvaliacao;
        this.controleUnidadeDeMedida = controleUnidadeDeMedida;
    }

    //metodos de ControleRepositorioReceita

    public void salvarReceita(Receita receita) {
        this.controleReceita.salvarReceita(receita);
    }
    public void excluirReceita(Receita receita) {
        this.controleReceita.excluirReceita(receita);
    }
    public void atualizarReceita(Receita receita) {this.controleReceita.atualizarReceita(receita);}
    public List<Receita> buscarReceitaPorAutor(Usuario autor) {return this.controleReceita.buscarReceitaPorAutor(autor);}
    public List<Receita> buscarReceitaPorAutor(String autor) {return this.controleReceita.buscarReceitaPorAutor(autor);}
    public List<Receita> buascarReceitaPorTitulo(String nome){return this.controleReceita.buscarReceitaPorTitulo(nome);}
    public List<Receita> buscarReceitaPorAvaliacao (Avaliacao avalicao){return this.controleReceita.buscarReceitaPorAvaliacao(avalicao);}
    public List<Receita> buscarReceitaPorAvaliacao (int avalicao){return this.controleReceita.buscarReceitaPorAvaliacao(avalicao);}
    public List<Receita> buscarReceitaPorIngrediente(String ingrediente){
        return this.controleReceita.buscarReceitaPorIngrediente(ingrediente);
    }
    public List<Receita> buscarReceitasAleatorias(){
        return this.controleReceita.buscarReceitasAleatorias();
    }
    public Receita buscarReceitaPorAutorETitulo(Usuario autor, String titulo){
        return this.controleReceita.buscarReceitaPorAutorETitulo(autor, titulo);
    }
    public Receita buscarReceitaPorAutorETitulo(String autor, String titulo){
        return this.controleReceita.buscarReceitaPorAutorETitulo(autor, titulo);
    }


    //metodos de ControleUsuario
    public  void excluirUsuario(Usuario usuario){ this.controleUsuario.excluirUsuario(usuario);}
    public void atualizarUsuario(Usuario usuario){this.controleUsuario.atualizarUsuario(usuario);}
    public  Usuario recuperarUsuarioPorId(int id){return this.controleUsuario.recuperarUsuarioPorId(id);}
    public Usuario recuperarUsuarioPorCpf(String cpf) throws UsuarioInexistenteException{ return this.controleUsuario.recuperarUsuarioPorCpf(cpf);}
    public Usuario recuperarUsuarioPorEmail(InternetAddress email) throws UsuarioInexistenteException {return this.controleUsuario.recuperarUsuarioPorEmail(email);}

    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) throws AddressException, NullPointerException, UsuarioJaExistenteException  {return this.controleUsuario.criarUsuario(nome, senha, email, cpf);}
    public UsuarioChef criarUsuarioChef(Usuario usuario) throws NullPointerException{return controleUsuario.criarUsuarioChef(usuario);}
    public void addReceitafav(Usuario usuario, Receita receita){
        controleUsuario.addReceitasFav(usuario,receita);
    }
    public void criarAvaliacao(int nota, String comentario, Usuario usuario, Receita receita) throws NullPointerException{
        controleUsuario.criarAvalizacao(nota, comentario, usuario, receita);
    }
    public Ingrediente criarIngrediente(String nome) throws ObjetoJaExiste, NullPointerException {return this.controleUsuario.criarIngrediente(nome);}
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida) throws NullPointerException{
        return this.controleUsuario.criarItemIngrediente(ingrediente, quantidade, medida);
    }

    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String tempoDePreparo, String categoria)throws ReceitaJaExistenteException {this.controleUsuario.criarReceita( titulo,  autor,  ingredientes,  preparo,  tempoDePreparo, categoria);}
    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String tempoDePreparo, String categoria, String caminhoImagem) throws ReceitaJaExistenteException {
        controleUsuario.criarReceita(titulo, autor, ingredientes, preparo, tempoDePreparo, categoria, caminhoImagem);
    }
    public String salvarImagem(File arquivo, String nomeArquivo) throws IOException {
        return controleUsuario.salvarImagem(arquivo, nomeArquivo);
    }
    //metodos de  ControleReceita

    //metodos de ControleIngrediente
    public void excluirIngrediente(Ingrediente ingrediente){this.controleIngrediente.excluirIngrediente(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id){return this.controleIngrediente.buscarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome){return this.controleIngrediente.buscarIngredientePorNome(nome);}
    public List<Ingrediente> buscarIngrediente(){
        return controleIngrediente.buscarIngrediente();
    }

    //metodos de ControleUnidadeDeMedida
    public List<UnidadeMedida> buscarUnidadeDeMedida(){
        return controleUnidadeDeMedida.buscarUnidadeMedidas();
    }
}
