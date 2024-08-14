package controle;

import modelo.Usuario;
import modelo.Ingrediente;
import modelo.Receita;
import repositorio.RepositorioIDs;

public class ControleRepositorioIDs {
    private final RepositorioIDs repositorioIDs;

    public ControleRepositorioIDs(RepositorioIDs repositorioIDs) {
        this.repositorioIDs = repositorioIDs;
    }

    public void salvarIDsReceita(Receita receita) {
        this.repositorioIDs.salvarAtributo(receita);
    }

    public void salvarIDUsuario(Usuario usuario) {
        int idUsuario = usuario.getId();
        this.repositorioIDs.salvarAtributo(idUsuario);
    }
    public void salvarIDIngrediente(Ingrediente ingrediente) {
        int idIngrediente = ingrediente.getId();
        this.repositorioIDs.salvarAtributo(idIngrediente);
    }
}
