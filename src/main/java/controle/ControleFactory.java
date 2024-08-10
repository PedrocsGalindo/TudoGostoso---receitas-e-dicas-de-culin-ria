package controle;

import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleRepositorioUsuario controleRepositorioUsuario = new ControleRepositorioUsuario(new RepositorioUsuario());
        ControleRepositorioReceita controleRepositorioReceita = new ControleRepositorioReceita(new RepositorioReceita());
        ControleUsuario controleUsuario = new ControleUsuario(controleRepositorioUsuario);
        ControleReceita controleReceita = new ControleReceita(controleRepositorioReceita);
        ControleUsuarioChef controleUsuarioChef = new ControleUsuarioChef(controleRepositorioUsuario, controleReceita);

        return new Controle(controleRepositorioUsuario, controleRepositorioReceita, controleUsuario, controleReceita, controleUsuarioChef);
    }
}
