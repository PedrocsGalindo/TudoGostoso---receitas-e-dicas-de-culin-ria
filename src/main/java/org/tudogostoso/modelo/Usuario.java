package org.tudogostoso.modelo;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.mail.internet.InternetAddress;
import java.util.Objects;

public class Usuario implements Serializable {
    protected final int id; //começa do 1
    private String nome;
    private String senha;
    private InternetAddress email;
    private final String cpf;
    private List<Receita> receitasFav;
    private List<Ingrediente> listaDeCompra;

    //usuario não vai ser intanciado diretamente, quem vai intanciar vai ser controle, depois criar um factory
    public Usuario(String nome, String senha, InternetAddress email, String cpf, int id) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.receitasFav = new ArrayList<>();
        this.listaDeCompra = new ArrayList<>();
    }
    //construtor que so vai ser usado para poder Criar a UsuarioChef
    public Usuario(String nome, String senha, InternetAddress email, String cpf, List<Receita> receitasFav, int id){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.receitasFav = receitasFav;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    public String toString(){
        return(id + ": " + nome + ", " + email);
    }

    public void addReceitasFav(Receita receita) {
        this.receitasFav.add(receita);
    }

    public void removerReceitasFav(Receita receita) {
        this.receitasFav.remove(receita);
    }

    //gets e sets

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public InternetAddress getEmail() {
        return email;
    }

    public void setEmail(InternetAddress email) {
        this.email = email;
    }

    public String getCpf(){return cpf;}

    public List<Receita> getReceitasFav() {
        return receitasFav;
    }

    public void setReceitasFav(List<Receita> receitasFav) {
        this.receitasFav = receitasFav;
    }

    public List<Ingrediente> getListaDeCompra() {return listaDeCompra; }

    public void addListaDeCompra(Ingrediente ingrediente) {
        this.listaDeCompra.add(ingrediente);
    }
}
