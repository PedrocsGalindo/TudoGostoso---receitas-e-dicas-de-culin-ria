package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.modelo.Receita;

import java.util.List;

public interface IRepositorioReceitas {
    //metodos extendidos do repositorioGenerico
    void salvar(Receita objeto);
    void excluir(Receita objeto);
    void update(Receita objeto);
    int getLastId();
    List<Receita> buscar();
}
