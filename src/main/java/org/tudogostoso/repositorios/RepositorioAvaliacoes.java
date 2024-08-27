package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;

import java.util.List;
import java.util.NoSuchElementException;

public class RepositorioAvaliacoes extends RepositorioGenerico<Avaliacao> {

    public RepositorioAvaliacoes() {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioAvaliacoes/repositorio.ser");
    }
    public int getLastId(){
        int id;
        List<Avaliacao> avaliacaos = buscar();
        try {
            id = avaliacaos.get(avaliacaos.size() - 1).getId();
        } catch (NoSuchElementException e) {
            id = 0;
        }catch (IndexOutOfBoundsException e) {
            id = 0;
        }
        return id;
    }
}