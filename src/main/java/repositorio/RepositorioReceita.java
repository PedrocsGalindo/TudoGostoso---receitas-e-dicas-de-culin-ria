package repositorio;

import modelo.Avaliacao;
import modelo.Receita;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioReceita extends RepositorioGenerico<Receita> {

    public RepositorioReceita() {
        super("src/main/resourcers/repositorios/RepositorioReceitas/repositorio.ser");
    }

    public List<Receita> buscarPorTitulo(String nome) {
        List<Receita> items = this.buscar();
        return items.stream()
                .filter(receita -> receita.getTitulo().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    public List<Receita> buscarPorAutor(String autor) {
        List<Receita> items = this.buscar();
        return items.stream()
                .filter(receita -> receita.getAutor().getNome().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<Receita> buscarPorAvaliacao(Avaliacao avaliacao) {
        List<Receita> items = this.buscar();
        return items.stream()
                .filter(receita -> receita.getAvaliacoes().stream()
                        .anyMatch(a -> a.equals(avaliacao)))
                .collect(Collectors.toList());
    }
}
