package repositorio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGenerico <T> {
    private List<T> lista = new ArrayList<>();

    public  T buscar(T obj){
        lista.contains(obj);
        System.out.println("elemente se encontra");
        return obj;
    }
    public void adicionar(T obj){
        lista.add(obj);
    }

    public void remover(T obj){
        lista.remove(obj);
    }
    public List<T> getLista(){
        return lista;
    }
    public void update(T obj){
        lista.set(lista.indexOf(obj), obj);
    }
}


