package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Avaliacao;
import org.tudogostoso.modelo.Receita;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class RepositorioReceitas extends RepositorioGenerico<Receita> {

    public RepositorioReceitas() {
        super("src/main/resources/org/tudogostoso/repositorios/RepositorioReceitas/repositorio.ser");
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
    public List<Receita> buscarPorAvaliacao(int avaliacao) {
        List<Receita> items = this.buscar();
        List<Receita> itemsDesejados = new ArrayList<>();

        for (Receita item : items) {
            if (item.getNota() == avaliacao){
                itemsDesejados.add(item);
            }
        }
        return itemsDesejados;

    }
    public int getLastId(){
        int id;
        List<Receita> receitas = buscar();
        try {
            id = receitas.get(receitas.size() - 1).getId();
        } catch (NoSuchElementException e) {
            id = 0;
        } catch (IndexOutOfBoundsException e) {
            id = 0;
        }
        return id;
    }
}
