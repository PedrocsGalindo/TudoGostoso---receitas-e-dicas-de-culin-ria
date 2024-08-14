package controle; // Certifique-se de que este é o nome correto do pacote

import modelo.ItemIngrediente;
import modelo.Receita;
import modelo.UsuarioChef;

import java.util.List;

public class ControleReceita {

    private final ControleRepositorioReceita controleRepositorioReceita;

    public ControleReceita(ControleRepositorioReceita controleRepositorioReceita) {
        this.controleRepositorioReceita = controleRepositorioReceita;
    }

    public Receita criarReceita(String titulo, UsuarioChef autor, List<ItemIngrediente> ingredientes, List<String> preparo, String modoDePreparo, String tempoDePreparo, String categoria) {
        int id = controleRepositorioReceita.getLastId();
        Receita receita = new Receita(titulo, autor, ingredientes, preparo, modoDePreparo, tempoDePreparo, categoria,id);
        controleRepositorioReceita.salvarReceita(receita);
        return receita;
    }
}
