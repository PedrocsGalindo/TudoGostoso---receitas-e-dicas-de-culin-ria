package controle;

import repositorio.RepositorioIngrediente;
import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleRepositorioIngrediente controleRepositorioIngrediente = new ControleRepositorioIngrediente(new RepositorioIngrediente());
        ControleReceita controleReceita = new ControleReceita(new RepositorioReceita());
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuario(),controleReceita);
        ControleIngrediente controleIngrediente = new ControleIngrediente(controleRepositorioIngrediente);

        return new Controle(controleRepositorioIngrediente, controleUsuario, controleReceita, controleIngrediente);
    }
}
