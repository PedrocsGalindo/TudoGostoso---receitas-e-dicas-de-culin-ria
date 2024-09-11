package org.tudogostoso.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter@Setter@ToString

public class Avaliacao implements Serializable {
    private final int id;
    private int nota;
    private String comentario;
    private final LocalDateTime data;
    private final Boolean cozinhou;
    private final Usuario usuario;
    private final Receita receita;


    public Avaliacao(int nota, String comentario, Boolean cozinhou, Usuario usuario, Receita receita, int id) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDateTime.now();
        this.cozinhou = cozinhou;
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


}

