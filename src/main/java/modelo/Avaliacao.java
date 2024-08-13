package modelo;

import java.io.Serializable;
import java.util.Date;

public class Avaliacao implements Serializable {
    private static int ids = 0;
    private final int id;
    private int nota;
    private String comentario;
    private final Date data;
    private final Usuario usuario;
    private final Receita receita;


    public Avaliacao(int nota, String comentario, Date data, Usuario usuario, Receita receita) {
        this.id = gerarId();
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.usuario = usuario;
        this.receita = receita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return id == avaliacao.id;
    }

    public int gerarId(){
        return ++ ids;
    }

    public int getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Receita getReceita() {
        return receita;
    }
}
