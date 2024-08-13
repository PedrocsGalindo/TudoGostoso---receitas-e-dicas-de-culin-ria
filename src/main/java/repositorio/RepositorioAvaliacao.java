package repositorio;

import modelo.Avaliacao;

public class RepositorioAvaliacao extends RepositorioGenerico<Avaliacao> {

    public RepositorioAvaliacao(String filePath) {
        super("src/main/resourcers/repositorios/RepositorioAvaliacao/repositorio.ser");
    }
}
