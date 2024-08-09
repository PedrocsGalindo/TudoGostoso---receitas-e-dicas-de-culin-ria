package controle;

import repositorio.RepositorioReceita;
import repositorio.RepositorioUsuario;

public class ControleFactory {

    public static Controle criarControleGeral() {
        ControleRepositorioUsuario controleRepositorioUsuario = new ControleRepositorioUsuario(new RepositorioUsuario());
        ControleRepositorioReceita controleRepositorioReceita = new ControleRepositorioReceita(new RepositorioReceita());
        ControleUsuario controleUsuario = new ControleUsuario(controleRepositorioUsuario);

        return new Controle(controleRepositorioUsuario, controleRepositorioReceita, controleUsuario);
    }
}
