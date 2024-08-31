package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Ingrediente;

import java.util.NoSuchElementException;

public interface IRepositorioIngredientes {
    int getLastId();

    Ingrediente busarIngredientePorId(int id) throws NoSuchElementException;

    Ingrediente buscarIngredientePorNome(String nome);

    void salvar(Ingrediente ingrediente);

    void excluir(Ingrediente ingrediente);

}
