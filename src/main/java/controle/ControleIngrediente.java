package controle;

import modelo.Ingrediente;
import modelo.ItemIngrediente;
import modelo.UnidadeMedida;
import repositorio.RepositorioIngrediente;

public class ControleIngrediente {

    private final RepositorioIngrediente repositorio;

    public ControleIngrediente(RepositorioIngrediente repositorioIngrediente){
        this.repositorio = repositorioIngrediente;
    }

    public Ingrediente criarIngrediente(String nome){
        Ingrediente ingrediente = buscarIngredientePorNome(nome);
        if(ingrediente == null){
            ingrediente = new Ingrediente(nome);
            salvarIngrediente(ingrediente);
        } else {
            System.out.println("O ingrediente ja existe");
        }
        return ingrediente;
    }
    public void salvarIngrediente(Ingrediente ingrediente) {this.repositorio.salvar(ingrediente);}
    public void excluirIngrediente(Ingrediente ingrediente) {this.repositorio.excluir(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id) {return this.repositorio.busarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome) {return this.repositorio.buscarIngredientePorNome(nome);}
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida){
        return new ItemIngrediente(ingrediente, quantidade, medida);
    }
}
