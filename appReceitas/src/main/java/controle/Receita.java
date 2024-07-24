package controle;

import java.util.List;

public class Receita{
    private static int numIds = 0;
    private final int id;
    private String titulo;
    private List<Ingredientes> ingredientes;
    private List<String> preparo;
    private Nota nota;

    public Receita(String titulo, List<Ingredientes> ingredientes, List<String> preparo, Nota nota) {
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

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public List<String> getPreparo() {
        return preparo;
    }

    public Nota getNota() {
        return nota;
    }
}