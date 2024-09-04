package org.tudogostoso.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter@Setter@ToString
public class UsuarioChef extends Usuario {

    private List<Receita> minhasReceitas;

    public UsuarioChef(Usuario usuario) {
        super(usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getCpf(), usuario.getReceitasFav(), usuario.getListaDeCompra(), usuario.getId());
        this.minhasReceitas = new ArrayList<>();
    }

    public void addMinhasReceita(Receita receita) {
        this.minhasReceitas.add(receita);
    }

    public void removerMinhasReceitas(Receita receita) {
        this.minhasReceitas.remove(receita);
    }

    public List<Receita> getMinhasReceitas() {
        return minhasReceitas;
    }

    public void setMinhasReceitas(List<Receita> minhasReceitas) {
        this.minhasReceitas = minhasReceitas;
    }
}