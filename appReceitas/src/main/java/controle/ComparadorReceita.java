package controle;

import controle.comparadoresReceita.ComparadorReceitasPorTitulo;

import java.util.Comparator;

//retorna um compador
//para usar teria que ser sort(ComparadorReceitas.ComparadorReceitasPorTitulo())
public class ComparadorReceita {

    public static Comparator<Receita> ComparadorReceitasPorTitulo(){
        return new ComparadorReceitasPorTitulo();
    }
}
