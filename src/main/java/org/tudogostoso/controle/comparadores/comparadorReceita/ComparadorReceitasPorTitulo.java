package org.tudogostoso.controle.comparadores.comparadorReceita;

import org.tudogostoso.modelo.Receita;
import java.util.Comparator;

public class ComparadorReceitasPorTitulo implements Comparator<Receita> {

    @Override
    public int compare(Receita o1, Receita o2) {
        return o1.getTitulo().compareTo(o2.getTitulo());
    }
}
