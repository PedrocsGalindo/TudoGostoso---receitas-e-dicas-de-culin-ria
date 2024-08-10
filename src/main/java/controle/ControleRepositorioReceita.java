package controle;

import modelo.Receita;
import modelo.Usuario;
import repositorio.RepositorioReceita;

import java.util.List;

public class ControleRepositorioReceita {

    private final RepositorioReceita repositorioReceita;

    public ControleRepositorioReceita(RepositorioReceita repositorioReceita) {
        this.repositorioReceita = repositorioReceita;
    }

    public void salvarReceita(Receita receita) {
        repositorioReceita.salvar(receita);
    }
    public void excluirReceita(Receita receita) {
        repositorioReceita.excluir(receita);
    }
    public List<Receita> buscarReceitaPorAutor(Usuario autor) {
        return repositorioReceita.buscarPorAutor(autor.getNome());
    }
    public List<Receita> buscarReceitaPorAutor(String autor) {
        return repositorioReceita.buscarPorAutor(autor);
    }
}
