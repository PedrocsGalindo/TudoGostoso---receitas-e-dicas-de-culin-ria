package modelo;

import java.util.List;

public class ListaDeCompras {
    private int id;
    private String nome;
    private List<Ingrediente> itens;
    private Usuario usuario;


    public ListaDeCompras(int id, String nome, List<Ingrediente> itens, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.itens = itens;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getItens() {
        return itens;
    }

    public void setItens(List<Ingrediente> itens) {
        this.itens = itens;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

