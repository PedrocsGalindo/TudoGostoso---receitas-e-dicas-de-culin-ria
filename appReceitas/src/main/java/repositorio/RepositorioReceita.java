package repositorio;

import controle.Avaliacao;
import controle.Receita;
import controle.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioReceita extends RepositorioGenerico<Receita> {

    public RepositorioReceita() {
        super("appReceitas/out/production/appReceitas/repositorios/RepositorioReceitas/repositorio.ser");
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

    public List<Receita> buscarPorAvaliacao(int avaliacao) {
        List<Receita> items = this.buscar();
        return items.stream()
                .filter(receita -> receita.getAvaliacao().getNota() == (avaliacao))
                .collect(Collectors.toList());
    }
}
