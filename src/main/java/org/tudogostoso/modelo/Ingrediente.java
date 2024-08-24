package org.tudogostoso.modelo;

import java.io.Serializable;

public class Ingrediente implements Serializable, Comparable<Ingrediente>{
    private static int ids;
    private int id;
    private String nome;


    public Ingrediente(String nome) {
        this.id = gerarId();
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

    public int gerarId() {
        return ++ids;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
