package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.modelo.Receita;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class RepositorioReceitas extends RepositorioGenerico<Receita> implements IRepositorioReceitas {

    public RepositorioReceitas() {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioReceitas/repositorio.ser");
    }

    public int getLastId(){
        int id;
        List<Receita> receitas = buscar();
        try {
            id = receitas.get(receitas.size() - 1).getId();
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            id = 0;
        }
        return id;
    }
}
