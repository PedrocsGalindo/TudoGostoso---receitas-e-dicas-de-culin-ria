package org.tudogostoso.controle;

import org.tudogostoso.repositorios.RepositorioIngredientes;
import org.tudogostoso.repositorios.RepositorioReceitas;
import org.tudogostoso.repositorios.RepositorioUsuarios;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleReceita controleReceita = new ControleReceita(new RepositorioReceitas());
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuarios(),controleReceita);
        ControleIngrediente controleIngrediente = new ControleIngrediente(new RepositorioIngredientes());

        return new Controle(controleUsuario, controleReceita, controleIngrediente);
    }
}
