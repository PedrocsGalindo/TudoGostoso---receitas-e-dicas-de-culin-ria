package controle;

import exceptions.UsuarioInexistenteException;
import modelo.*;

import javax.mail.internet.InternetAddress;
import java.util.List;

public class Controle {
    private final ControleRepositorioUsuario controleRepositorioUsuario;
    private final ControleRepositorioReceita controleRepositorioReceita;
    private final ControleRepositorioIngrediente controleRepositorioIngrediente;
    private final ControleUsuario controleUsuario;
    private final ControleReceita controleReceita;
    private final ControleUsuarioChef controleUsuarioChef;
    private final ControleIngrediente controleIngrediente;

    public Controle(ControleRepositorioUsuario controleRepositorioUsuario, ControleRepositorioReceita controleRepositorioReceita, ControleRepositorioIngrediente controleRepositorioIngrediente, ControleUsuario controleUsuario, ControleReceita controleReceita, ControleUsuarioChef controleUsuarioChef, ControleIngrediente controleIngrediente) {
        this.controleRepositorioUsuario = controleRepositorioUsuario;
        this.controleRepositorioReceita = controleRepositorioReceita;
        this.controleRepositorioIngrediente = controleRepositorioIngrediente;
        this.controleUsuario = controleUsuario;
        this.controleReceita = controleReceita;
        this.controleUsuarioChef = controleUsuarioChef;
        this.controleIngrediente = controleIngrediente;
    }

    //metodos de ControleRepositorioUsuario

    public  void excluirUsuario(Usuario usuario){ this.controleRepositorioUsuario.excluirUsuario(usuario);}
    public void atualizarUsuario(Usuario usuario){this.controleRepositorioUsuario.atualizarUsuario(usuario);}
    public  Usuario recuperarUsuarioPorId(int id){return this.controleRepositorioUsuario.recuperarUsuarioPorId(id);}
    public Usuario recuperarUsuarioPorCpf(String cpf) throws UsuarioInexistenteException{ return this.controleRepositorioUsuario.recuperarUsuarioPorCpf(cpf);}
    public Usuario recuperarUsuarioPorEmail(InternetAddress email) throws UsuarioInexistenteException {return this.controleRepositorioUsuario.recuperarUsuarioPorEmail(email);}

    //metodos de ControleRepositorioReceita

    public void salvarReceita(Receita receita) {
        this.controleRepositorioReceita.salvarReceita(receita);
    }
    public void excluirReceita(Receita receita) {
        this.controleRepositorioReceita.excluirReceita(receita);
    }
    public void atualizarReceita(Receita receita) {this.controleRepositorioReceita.atualizarReceita(receita);}
    public List<Receita> buscarReceitaPorAutor(Usuario autor) {return this.controleRepositorioReceita.buscarReceitaPorAutor(autor);}
    public List<Receita> buscarReceitaPorAutor(String autor) {return this.controleRepositorioReceita.buscarReceitaPorAutor(autor);}
    public List<Receita> buascarReceitaPorTitulo(String nome){return this.controleRepositorioReceita.buscarReceitaPorTitulo(nome);}
    public List<Receita> buscarReceitaPorAvaliacao (Avaliacao avalicao){return this.controleRepositorioReceita.buscarReceitaPorAvaliacao(avalicao);}

    //metodos de ControleRepositorioIngrediente

    //metodos de ControleUsuario

    public  Usuario criarUsuario(String nome, String senha, String email, String cpf) {return this.controleUsuario.criarUsuario(nome, senha, email, cpf);}
    public Usuario criarUsuarioChef(Usuario usuario) throws NullPointerException{return controleUsuario.criarUsuarioChef(usuario);}

    //metodos de ControleUsuarioChef

    public void criarReceita(String titulo, Usuario autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria){this.controleUsuarioChef.criarReceita( titulo,  autor,  ingredientes,  preparo,  modoDePreparo,  tempoDePreparo, categoria);}

    //metodos de  ControleReceita

    //metodos de ControleIngrediente
    public Ingrediente criarIngrediente(String nome){return this.controleIngrediente.criarIngrediente(nome);}
    public void excluirIngrediente(Ingrediente ingrediente){this.controleIngrediente.excluirIngrediente(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id){return this.controleIngrediente.buscarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome){return this.controleIngrediente.buscarIngredientePorNome(nome);}
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida){
        return this.controleIngrediente.criarItemIngrediente(ingrediente, quantidade, medida);
    }
}
