package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;

public class RepositorioAvaliacoes extends RepositorioGenerico<Avaliacao> {

    public RepositorioAvaliacoes(String filePath) {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioAvaliacoes/repositorio.ser");
    }
}