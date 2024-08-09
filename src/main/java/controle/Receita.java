package controle;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Receita implements Serializable, Comparable<Receita>{
    private static int numIds = 0;
    private final int id;
    private String titulo;
    private final Usuario autor;
    private List<Ingrediente> ingredientes;
    private List<String> preparo;// o arraylist de preparo Seria um guia por para o leitor?
    private List<Avaliacao> avaliacoes;
    private int nota = 0;
    private LocalDateTime horario;
    private String modoDePreparo;
    private String tempoDePreparo;
    private String categoria;


    public Receita(String titulo, Usuario autor, List<Ingrediente> ingredientes, List<String> preparo, String modoDePreparo,String tempoDePreparo, String categoria) {
        this.id = numIds++;
        this.autor = autor;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.horario = LocalDateTime.now();
        this.avaliacoes = new ArrayList<>();
        this.tempoDePreparo= tempoDePreparo;
        this.modoDePreparo = modoDePreparo;
        this.categoria = categoria;
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

    public int getNota() {
        return nota;
    }

    public String getModoDePreparo(){
        return modoDePreparo;
    }
    public String getCategoria(){
        return categoria;

    }
    public  String getTempoDePreparo(){
        return tempoDePreparo;
    }

    public void atualizarNota(){
        int soma = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota(); //soma todas as avaliações
        }
        this.nota = soma/this.avaliacoes.size();
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

    public List<Avaliacao> getAvaliacoes(){return this.avaliacoes;}

    public void adicioarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }// é pra esse método ser aqui mesmo?

    public  void removerAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.remove(avaliacao);
    }
    // é pra esse método ser aqui mesmo?

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public LocalDateTime getHorario(){
        return this.horario;
    }


    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", ingredientes=" + ingredientes +
                ", preparo=" + preparo +
                ", avaliacoes=" + avaliacoes +
                ", nota=" + nota +
                ", horario=" + horario +
                ", modoDePreparo='" + modoDePreparo + '\'' +
                ", tempoDePreparo='" + tempoDePreparo + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
