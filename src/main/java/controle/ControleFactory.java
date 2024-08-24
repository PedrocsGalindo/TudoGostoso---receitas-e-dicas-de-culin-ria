package controle;

import repositorio.RepositorioIngrediente;
import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleReceita controleReceita = new ControleReceita(new RepositorioReceita());
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuario(),controleReceita);
        ControleIngrediente controleIngrediente = new ControleIngrediente(new RepositorioIngrediente());

        return new Controle(controleUsuario, controleReceita, controleIngrediente);
    }
}
