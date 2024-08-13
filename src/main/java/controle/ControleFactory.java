package controle;

import repositorio.RepositorioIngrediente;
import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleRepositorioUsuario controleRepositorioUsuario = new ControleRepositorioUsuario(new RepositorioUsuario());
        ControleRepositorioReceita controleRepositorioReceita = new ControleRepositorioReceita(new RepositorioReceita());
        ControleRepositorioIngrediente controleRepositorioIngrediente = new ControleRepositorioIngrediente(new RepositorioIngrediente());
        ControleUsuario controleUsuario = new ControleUsuario(controleRepositorioUsuario);
        ControleReceita controleReceita = new ControleReceita(controleRepositorioReceita);
        ControleUsuarioChef controleUsuarioChef = new ControleUsuarioChef(controleRepositorioUsuario, controleReceita);
        ControleIngrediente controleIngrediente = new ControleIngrediente(controleRepositorioIngrediente);

        return new Controle(controleRepositorioUsuario, controleRepositorioReceita, controleRepositorioIngrediente, controleUsuario, controleReceita, controleUsuarioChef, controleIngrediente);
    }
}
