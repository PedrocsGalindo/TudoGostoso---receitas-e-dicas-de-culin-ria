package repositorio;

import modelo.Receita;


public class Mainteste {
    public static void main(String[] args) {

        Receita a = new Receita("inicio",null,null,null,null,null,null, id);
        Receita b = new Receita("inicio2",null,null,null,null,null,null, id);


        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        RepositorioReceita repositorioReceita =  new RepositorioReceita();


        repositorioReceita.salvar(a);
        repositorioReceita.salvar(b);


        System.out.println( repositorioReceita.buscar());

            
    }


    }

