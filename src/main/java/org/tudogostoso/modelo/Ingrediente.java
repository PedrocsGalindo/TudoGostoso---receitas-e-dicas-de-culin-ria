package org.tudogostoso.modelo;

import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
@Getter @Setter
public class Ingrediente implements Serializable, Comparable<Ingrediente>{
    private int id;
    private String nome;


    public Ingrediente(String nome, int id) {
        this.id = id;
        this.nome = nome;
    }
    //a ordem naturel é baseado no nome, ordem alfabetica
    @Override
    public int compareTo(Ingrediente o) {
        return this.nome.compareTo(o.getNome());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente ingrediente = (Ingrediente) o;
        return this.nome.equals(ingrediente.getNome());
    }

    public Ingrediente(String nome) {
        this.nome = nome;}

    @Override
    public String toString() {
        return  nome;
    }
}
