package controle;

import java.io.Serializable;

public class Ingrediente implements Serializable, Comparable<Ingrediente>{
    private int id;
    private String nome;
    private String quantidade;
    private String unidade;


    public Ingrediente(int id, String nome, String quantidade, String unidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
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
