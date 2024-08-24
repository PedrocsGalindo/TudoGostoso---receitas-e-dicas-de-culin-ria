package org.tudogostoso.controle.comparadores.comparadorReceita;

import org.tudogostoso.modelo.Receita;
import java.util.Comparator;

//retorna um comparator


public class ComparadorReceita{

    //para usar teria que ser sort(ComparadorReceitas.ComparadorReceitasPorTitulo())
    public static Comparator<Receita> ComparadorReceitasPorTitulo(){
        return new ComparadorReceitasPorTitulo();
    }
}
