package controle;

import repositorio.RepositorioIngrediente;
import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleRepositorioReceita controleRepositorioReceita = new ControleRepositorioReceita(new RepositorioReceita());
        ControleRepositorioIngrediente controleRepositorioIngrediente = new ControleRepositorioIngrediente(new RepositorioIngrediente());
        ControleReceita controleReceita = new ControleReceita(controleRepositorioReceita);
        ControleUsuario controleUsuario = new ControleUsuario(new RepositorioUsuario(),controleReceita);
        ControleIngrediente controleIngrediente = new ControleIngrediente(controleRepositorioIngrediente);

        return new Controle( controleRepositorioReceita, controleRepositorioIngrediente, controleUsuario, controleReceita, controleIngrediente);
    }
}
