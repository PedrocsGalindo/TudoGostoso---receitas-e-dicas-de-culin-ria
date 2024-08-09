package modelo;

import java.io.Serializable;

public class Ingrediente implements Serializable, Comparable<Ingrediente>{
    private int id;
    private String nome;


    public Ingrediente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //a ordem naturel é baseado no nome, ordem alfabetica
    @Override
    public int compareTo(Ingrediente o) {
        return this.nome.compareTo(o.getNome());
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
