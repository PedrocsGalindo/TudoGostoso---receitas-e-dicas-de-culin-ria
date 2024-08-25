package org.tudogostoso.controle;

import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.modelo.Receita;
import org.tudogostoso.modelo.Usuario;
import org.tudogostoso.repositorios.RepositorioReceitas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControleReceita {

    private final RepositorioReceitas repositorioReceita;

    public ControleReceita(RepositorioReceitas repositorioReceita) {
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
    public List<Receita> buscarReceitasAleatorias() {
        List<Receita> receitasAleatorias = new ArrayList<>();
        Random random = new Random();

        while (receitasAleatorias.size() < 3) {
            int idAleatorio = random.nextInt(getLastId()) + 1;
            Receita receita = buscarReceitaPorId(idAleatorio);

            if (receita != null && !receitasAleatorias.contains(receita)) {
                receitasAleatorias.add(receita);
            }
        }

        return receitasAleatorias;
    }

    public Receita buscarReceitaPorId(int id) {
        List<Receita> receitas = repositorioReceita.buscar();

        for (Receita receita : receitas) {
            if (receita.getId() == id) {
                return receita;
            }
        }
        return null; // Retorna null se a receita com o ID não for encontrada
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
