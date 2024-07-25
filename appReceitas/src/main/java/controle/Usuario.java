package controle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Usuario implements Serializable {
    private static int numIds = 0;
    private final int id;
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private List<Receita> receitasFav;
    private List<Receita> minhasReceitas;


    public Usuario(String nome, String senha, String email, String cpf  ) {
        this.id = numIds++;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.receitasFav = new ArrayList<Receita>();
        this.minhasReceitas = new ArrayList<Receita>();
    }

    public boolean equals(Receita receita) {
        if (receita.getId() == getId()) {
            return true;
        }
        return false;
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

    public void addMinhasReceitas(Receita receita) {
        this.minhasReceitas.add(receita);
    }

    public void removerMinhasReceitas(Receita receita) {
        this.minhasReceitas.remove(receita);
    }

    //gets e sets

    public static int getNumIds() {
        return numIds;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Receita> getReceitasFav() {
        return receitasFav;
    }

    public void setReceitasFav(List<Receita> receitasFav) {
        this.receitasFav = receitasFav;
    }

    public List<Receita> getMinhasReceitas() {
        return minhasReceitas;
    }

    public void setMinhasReceitas(List<Receita> minhasReceitas) {
        this.minhasReceitas = minhasReceitas;
    }
}
