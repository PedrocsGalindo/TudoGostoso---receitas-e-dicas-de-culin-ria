package repositorio;

import controle.Receita;

public class Main {
    public static void main(String[] args) {

        RepositorioReceita repositorioReceita = new RepositorioReceita();
        Receita receita1 = new Receita("test",null,null,null);
        repositorioReceita.adicionar(receita1);
        repositorioReceita.buscar(receita1);

    }
}