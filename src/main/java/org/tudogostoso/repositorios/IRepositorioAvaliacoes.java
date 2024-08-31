package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;

import java.util.List;

public interface IRepositorioAvaliacoes {
    int getLastId();
    void salvar(Avaliacao avaliacao);
    void excluir(Avaliacao avaliacao);
    void update(Avaliacao avaliacao);
    List<Avaliacao> buscar();
}
