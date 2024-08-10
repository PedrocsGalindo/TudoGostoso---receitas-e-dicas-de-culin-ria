package repositorio;

import modelo.ListaDeCompras;
import modelo.Receita;

public class Mainteste {
    public static void main(String[] args) {

        Receita a = new Receita("inicio",null,null,null,null,null,null);
        Receita b = new Receita("inicio2",null,null,null,null,null,null);
        ListaDeCompras a1 = new ListaDeCompras(4,"das",null,null);
        RepositorioReceita repositorioReceita =  new RepositorioReceita();
        RepositorioListaDeCompras repositorioListaDeCompras = new RepositorioListaDeCompras();

        repositorioReceita.salvar(a);
        repositorioReceita.salvar(b);
        repositorioListaDeCompras.salvar(a1);



        System.out.println( repositorioReceita.buscar());
        System.out.println(repositorioListaDeCompras.buscar());

            
    }


    }

