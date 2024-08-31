package org.tudogostoso.controle;

import org.tudogostoso.repositorios.*;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleAvaliacao controleAvaliacao = new ControleAvaliacao(new RepositorioAvaliacoes());
        ControleReceita controleReceita = new ControleReceita(new RepositorioReceitas());
        ControleIngrediente controleIngrediente = new ControleIngrediente(new RepositorioIngredientes());
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuarios(),controleReceita, controleAvaliacao,controleIngrediente, new RepositorioImagens());
        ControleUnidadeDeMedida controleUnidadeDeMedida = new ControleUnidadeDeMedida();

        return new Controle(controleUsuario, controleReceita, controleIngrediente, controleAvaliacao, controleUnidadeDeMedida);
    }
}
