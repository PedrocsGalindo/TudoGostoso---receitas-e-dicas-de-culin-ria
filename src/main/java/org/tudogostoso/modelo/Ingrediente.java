package org.tudogostoso.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter @Setter@ToString
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

    public Ingrediente(String nome) {
        this.nome = nome;}

}
