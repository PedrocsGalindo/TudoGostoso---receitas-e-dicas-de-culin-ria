package controle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Receita implements Serializable {
    private static int numIds = 0;
    private final int id;
    private String titulo;
    private List<Ingrediente> ingredientes;
    private List<String> preparo;
    private Nota nota;
    private LocalDateTime horario;

    public Receita(String titulo, List<Ingrediente> ingredientes, List<String> preparo, Nota nota, LocalDateTime horario) {
        this.id = numIds++;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.nota = nota;
        this.horario = horario;
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

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPreparo(List<String> preparo) {
        this.preparo = preparo;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
