package repositorio;

import modelo.Ingrediente;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RepositorioIngrediente extends RepositorioGenerico<Ingrediente>{

    public RepositorioIngrediente(){
        super("src/main/resourcers/repositorios/RepositorioIngredientes/repositorio.ser");
    }

    public Ingrediente busarIngredientePorId(int id) throws NoSuchElementException{
        List<Ingrediente> ingredientes = buscar();
        Iterator <Ingrediente> interador= ingredientes.iterator();
        Ingrediente ingrediente = null;
        while(interador.hasNext()){
            ingrediente = interador.next();
            if(ingrediente.getId() == id){
                break;
            }
        }
        if(ingrediente == null){
            throw  new NoSuchElementException("Não existe nenhum ingrediente com o id "+id);
        }
        return ingrediente;
    }

    public Ingrediente buscarIngredientePorNome(String nome){
        List<Ingrediente> ingredientes = buscar();
        Iterator <Ingrediente> iterador= ingredientes.iterator();
        Ingrediente ingrediente = null;
        while(iterador.hasNext()){
            ingrediente = iterador.next();
            if(ingrediente.getNome().equals(nome)){
                break;
            }else if (!iterador.hasNext()){
                ingrediente = null;
            }
        }
        return ingrediente;
    }
}
