package org.tudogostoso.controle;

import org.tudogostoso.repositorios.RepositorioAvaliacoes;
import org.tudogostoso.repositorios.RepositorioIngredientes;
import org.tudogostoso.repositorios.RepositorioReceitas;
import org.tudogostoso.repositorios.RepositorioUsuarios;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleAvaliacao controleAvaliacao = new ControleAvaliacao(new RepositorioAvaliacoes());
        ControleReceita controleReceita = new ControleReceita(new RepositorioReceitas());
        ControleIngrediente controleIngrediente = new ControleIngrediente(new RepositorioIngredientes());
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuarios(),controleReceita, controleAvaliacao,controleIngrediente);
        ControleUnidadeDeMedida controleUnidadeDeMedida = new ControleUnidadeDeMedida();

        return new Controle(controleUsuario, controleReceita, controleIngrediente, controleAvaliacao, controleUnidadeDeMedida);
    }
}
