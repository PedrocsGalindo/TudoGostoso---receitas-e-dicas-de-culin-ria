package repositorio;

import controle.Receita;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioReceita extends RepositorioGenerico<Receita> {

    public List<Receita> buscarPorNome(String nome) {
        return items.stream()
                .filter(receita -> receita.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    public List<Receita> buscarPorAutor(String autor) {
        return items.stream()
                .filter(receita -> receita.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<Receita> buscarPorAvaliacao(int avaliacao) {
        return items.stream()
                .filter(receita -> receita.getAvaliacao() == avaliacao)
                .collect(Collectors.toList());
    }
}
