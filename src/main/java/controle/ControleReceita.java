package controle;

import modelo.Avaliacao;
import modelo.Receita;
import modelo.Usuario;
import repositorio.RepositorioReceita;

import java.util.List;

public class ControleReceita {

    private final RepositorioReceita repositorioReceita;

    public ControleReceita(RepositorioReceita repositorioReceita) {
        this.repositorioReceita = repositorioReceita;
    }

    public void salvarReceita(Receita receita) {
        repositorioReceita.salvar(receita);
    }
    public void excluirReceita(Receita receita) {
        repositorioReceita.excluir(receita);
    }
    public void atualizarReceita(Receita receita) {
        repositorioReceita.update(receita);
    }
    public List<Receita> buscarReceitaPorAutor(Usuario autor) {
        return repositorioReceita.buscarPorAutor(autor.getNome());
    }
    public List<Receita> buscarReceitaPorAutor(String autor) {
        return repositorioReceita.buscarPorAutor(autor);
    }
    public List<Receita> buscarReceitaPorTitulo(String nome) {
        return repositorioReceita.buscarPorTitulo(nome);
    }
    public List<Receita> buscarReceitaPorAvaliacao(Avaliacao avaliacao) {
        return repositorioReceita.buscarPorAvaliacao(avaliacao);
    }
    public Receita buscarReceitaPorAutorETitulo(Usuario autor, String nome) {
        List<Receita> receitasAutor = repositorioReceita.buscarPorAutor(autor.getNome());
        Receita receitaDesejada = null;
        for (Receita receita : receitasAutor) {
            if (receita.getTitulo().equals(nome)) {
                receitaDesejada = receita;
            }
        }
        return receitaDesejada;
    }
    public int getLastId() {
        int id;
        try {
            id = repositorioReceita.getLastId();
        } catch (NullPointerException e){
            id = 0;
        }
        return id;
    }
}
