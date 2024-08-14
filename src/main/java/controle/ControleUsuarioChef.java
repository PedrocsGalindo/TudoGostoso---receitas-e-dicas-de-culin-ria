package controle;

import modelo.ItemIngrediente;
import modelo.Receita;
import modelo.UsuarioChef;

import java.util.List;

public class ControleUsuarioChef {


    private final ControleRepositorioUsuario controleRepositorioUsuario;
    private final ControleReceita controleReceita;

    public ControleUsuarioChef(ControleRepositorioUsuario controleRepositorioUsuario, ControleReceita controleReceita) {
        this.controleRepositorioUsuario = controleRepositorioUsuario;
        this.controleReceita = controleReceita;
    }

    public void criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria){

        autor.criarReceita(controleReceita.cadastrarReceita(new Receita(titulo, autor, ingredientes, preparo, modoDePreparo, tempoDePreparo, categoria, id)));
    }
}
