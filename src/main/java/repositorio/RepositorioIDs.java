package repositorio;

import java.util.List;
import modelo.Usuario;
import modelo.Ingrediente;
import modelo.Receita;

public class RepositorioIDs extends RepositorioGenerico<Object> {

    public RepositorioIDs() {
        super("src/main/resources/repositorios/RepositorioIDs/repositorio.ser");
    }
}