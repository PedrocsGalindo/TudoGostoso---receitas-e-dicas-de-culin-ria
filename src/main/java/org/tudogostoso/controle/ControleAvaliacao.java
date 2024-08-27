package org.tudogostoso.controle;


import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.repositorios.RepositorioAvaliacoes;

import java.util.List;

public class ControleAvaliacao {
    private final RepositorioAvaliacoes repositorio;

    public ControleAvaliacao(RepositorioAvaliacoes repositorioAvaliacao){
        this.repositorio= repositorioAvaliacao;

    }
    public void salvar(Avaliacao avaliacao){
        repositorio.salvar(avaliacao);
    }
    public void excluir(Avaliacao avaliacao){
        repositorio.excluir(avaliacao);
    }
    public void update(Avaliacao avaliacao){
        repositorio.update(avaliacao);
    }
    public List<Avaliacao> buscar(){
        return repositorio.buscar();
    }
    public int getLastId(){
        return repositorio.getLastId();
    }
}
