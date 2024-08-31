package org.tudogostoso.controle;

import org.tudogostoso.modelo.Ingrediente;
import org.tudogostoso.modelo.ItemIngrediente;
import org.tudogostoso.modelo.UnidadeMedida;
import org.tudogostoso.repositorios.IRepositorioIngredientes;

public class ControleIngrediente {

    private final IRepositorioIngredientes repositorio;

    public ControleIngrediente(IRepositorioIngredientes repositorioIngrediente){
        this.repositorio = repositorioIngrediente;
    }
    public int getLastId(){
        return repositorio.getLastId();
    }
    public void salvarIngrediente(Ingrediente ingrediente) {this.repositorio.salvar(ingrediente);}
    public void excluirIngrediente(Ingrediente ingrediente) {this.repositorio.excluir(ingrediente);}
    public Ingrediente buscarIngredientePorId(int id) {return this.repositorio.busarIngredientePorId(id);}
    public Ingrediente buscarIngredientePorNome(String nome) {return this.repositorio.buscarIngredientePorNome(nome);}
    public ItemIngrediente criarItemIngrediente(Ingrediente ingrediente, double quantidade, UnidadeMedida medida){
        return new ItemIngrediente(ingrediente, quantidade, medida);
    }
}
