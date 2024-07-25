package controle;

import java.util.Date;

public class Avaliacao {
    private int id;
    private int nota;
    private String comentario;
    private Date data;
    private Usuario usuario;
    private Receita receita;


    public Avaliacao(int id, int nota, String comentario, Date data, Usuario usuario, Receita receita) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.usuario = usuario;
        this.receita = receita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
