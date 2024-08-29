package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.modelo.Receita;

import java.util.List;

public interface IRepositorioReceitas {
    List<Receita> buscarPorTitulo(String nome);

    List<Receita> buscarPorAutor(String autor);

    List<Receita> buscarPorAvaliacao(Avaliacao avaliacao);

    List<Receita> buscarPorAvaliacao(int avaliacao);

    List<Receita> buscarPorIngrediente(String ingrediente);
    //metodos extendidos do repositorioGenerico
    void salvar(Receita objeto);
    void excluir(Receita objeto);
    void update(Receita objeto);
    int getLastId();
    List<Receita> buscar();
}
