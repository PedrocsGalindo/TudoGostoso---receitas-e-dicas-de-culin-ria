package controle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;


public class Usuario implements Serializable {
    private static int numIds = 0;
    private final int id;
    private String nome;
    private String senha;
    private InternetAddress email;
    private final String cpf;
    private List<Receita> receitasFav;


    public Usuario(String nome, String senha, String email, String cpf  ) {
        this.id = numIds++;
        this.nome = nome;
        this.senha = senha;
        try{
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            this.email = emailAddress;

        } catch (AddressException e) {
            System.out.println("Email invalido");
        }
        this.cpf = cpf;
        this.receitasFav = new ArrayList<Receita>();
    }

    public boolean equals(Receita receita) {
        return receita.getId() == getId();
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

}
