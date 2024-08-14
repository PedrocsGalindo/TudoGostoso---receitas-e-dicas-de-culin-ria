package controle;

import exceptions.ReceitaJaExistenteException;
import modelo.ItemIngrediente;
import modelo.Receita;
import modelo.Usuario;
import modelo.UsuarioChef;

import java.util.List;

public class ControleUsuarioChef {


    private final ControleRepositorioUsuario controleRepositorioUsuario;
    private final ControleReceita controleReceita;

    public ControleUsuarioChef(ControleRepositorioUsuario controleRepositorioUsuario, ControleReceita controleReceita) {
        this.controleRepositorioUsuario = controleRepositorioUsuario;
        this.controleReceita = controleReceita;
    }

    public void criarReceita(String titulo, Usuario autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria) {
        int id = controleReceita.getLastId() + 1;
        Receita receita = new Receita(id, titulo, (UsuarioChef) autor, ingredientes, preparo, modoDePreparo, tempoDePreparo, categoria);
        try{
            controleReceita.cadastrarReceita(receita);
        } catch (ReceitaJaExistenteException e ){
            System.out.println(e.getMessage());
        }
    }
}