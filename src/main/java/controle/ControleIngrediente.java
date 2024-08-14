package controle;

import modelo.Ingrediente;
import modelo.ItemIngrediente;
import modelo.UnidadeMedida;

public class ControleIngrediente {

    private final ControleRepositorioIngrediente controleRepositorioIngrediente;

    public ControleIngrediente(ControleRepositorioIngrediente controleRepositorioIngrediente){
        this.controleRepositorioIngrediente = controleRepositorioIngrediente;
    }

    public Ingrediente criarIngrediente(String nome){
        Ingrediente ingrediente = buscarIngredientePorNome(nome);
        if(ingrediente == null){
            ingrediente = new Ingrediente(nome);
            controleRepositorioIngrediente.salvarIngrediente(ingrediente);
        } else {
            System.out.println("O ingrediente ja existe");
        }
        return ingrediente;
    }
    public void excluirIngrediente(Ingrediente ingrediente){controleRepositorioIngrediente.excluirIngrediente(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id){return controleRepositorioIngrediente.buscarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome){return controleRepositorioIngrediente.buscarIngredientePorNome(nome);}
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida){
        ItemIngrediente itemIngrediente = new ItemIngrediente(ingrediente, quantidade, medida);
        return itemIngrediente;
    }
}
