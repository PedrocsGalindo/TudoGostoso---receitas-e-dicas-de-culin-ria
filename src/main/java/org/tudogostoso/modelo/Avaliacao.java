package org.tudogostoso.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Avaliacao implements Serializable {
    private final int id;
    private int nota;
    private String comentario;
    private final LocalDateTime data;
    private final Usuario usuario;
    private final Receita receita;


    public Avaliacao(int nota, String comentario, Usuario usuario, Receita receita, int id) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDateTime.now();
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

    public LocalDateTime getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Receita getReceita() {
        return receita;
    }
}

