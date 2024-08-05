package controle;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class Receita implements Serializable, Comparable<Receita>{
    private static int numIds = 0;
    private final int id;
    private String titulo;
    private final Usuario autor;
    private List<Ingrediente> ingredientes;
    private List<String> preparo;
    private Avaliacao avaliacao;
    private Nota nota;
    private LocalDateTime horario;

    public Receita(String titulo, Usuario autor, List<Ingrediente> ingredientes, List<String> preparo, Nota nota) {
        this.id = numIds++;
        this.autor = autor;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.nota = nota;
        this.horario = LocalDateTime.now();
    }
    //ordem natural de receitas é baseado no horario
    @Override
    public int compareTo(Receita o){
        return this.horario.compareTo(o.getHorario());
    }

    public boolean equals(Receita receita) {
        return receita.getId() == getId();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getAutor() { return  this.autor;}

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public List<String> getPreparo() {
        return preparo;
    }

    public Nota getNota() {
        return nota;
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

    public Avaliacao getAvaliacao(){return this.avaliacao;}

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public LocalDateTime getHorario(){
        return this.horario;
    }


}
