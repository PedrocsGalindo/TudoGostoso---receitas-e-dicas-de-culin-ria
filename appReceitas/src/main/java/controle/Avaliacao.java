package controle;

import java.io.Serializable;
import java.util.Date;

public class Avaliacao implements Serializable {
    private final int id;
    private int nota;
    private String comentario;
    private final Date data;
    private final Usuario usuario;
    private final Receita receita;


    public Avaliacao(int id, int nota, String comentario, Date data, Usuario usuario, Receita receita) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.usuario = usuario;
        this.receita = receita;
    }

    public boolean equals(Avaliacao a) {
        return this.getId() == a.getId();

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
