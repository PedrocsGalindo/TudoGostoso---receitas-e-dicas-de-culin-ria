package controle;

import java.io.Serializable;
import java.util.List;

public class Receita implements Serializable {
    private static int numIds = 0;
    private final int id;
    private String titulo;
    private List<Ingrediente> ingredientes;
    private List<String> preparo;
    private Nota nota;

    public Receita(String titulo, List<Ingrediente> ingredientes, List<String> preparo, Nota nota) {
        this.id = numIds++;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.nota = nota;
    }

    public boolean equals(Receita receita) {
        if (receita.getId() == getId()) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public List<String> getPreparo() {
        return preparo;
    }

    public Nota getNota() {
        return nota;
    }
}