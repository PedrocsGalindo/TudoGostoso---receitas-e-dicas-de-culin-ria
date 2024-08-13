package controle;

import modelo.Ingrediente;
import repositorio.RepositorioIngrediente;

public class ControleRepositorioIngrediente {

    private final RepositorioIngrediente repositorio;

    public ControleRepositorioIngrediente(RepositorioIngrediente repositorioIngrediente) {
        this.repositorio = repositorioIngrediente;
    }

    public void salvarIngrediente(Ingrediente ingrediente) {this.repositorio.salvar(ingrediente);}
    public void excluirIngrediente(Ingrediente ingrediente) {this.repositorio.excluir(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id) {return this.repositorio.busarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome) {return this.repositorio.buscarIngredientePorNome(nome);}

}
