package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;

import java.util.List;
import java.util.NoSuchElementException;

public class RepositorioAvaliacoes extends RepositorioGenerico<Avaliacao> implements IRepositorioAvaliacoes {

    public RepositorioAvaliacoes() {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioAvaliacoes/repositorio.ser");
    }
    @Override
    public int getLastId(){
        int id;
        List<Avaliacao> avaliacaos = buscar();
        try {
            id = avaliacaos.get(avaliacaos.size() - 1).getId();
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            id = 0;
        }
        return id;
    }
}