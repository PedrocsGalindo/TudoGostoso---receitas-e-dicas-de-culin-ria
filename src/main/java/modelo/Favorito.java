package modelo;

public class Favorito {
    private int id;
    private Usuario usuario;
    private Receita receita;


    public Favorito(int id, Usuario usuario, Receita receita) {
        this.id = id;
        this.usuario = usuario;
        this.receita = receita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}

