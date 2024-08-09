package controle;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

public class UsuarioChef extends Usuario{

    private List<Receita> minhasReceitas;

    public UsuarioChef(String nome, String senha, InternetAddress email, String cpf, List<Receita> minhasReceitas) {
        super(nome, senha, email, cpf);
        setReceitasFav(new ArrayList<Receita>());
        this.minhasReceitas = new ArrayList<Receita>();
    }

    public void addMinhasReceitas(Receita receita) {
        this.minhasReceitas.add(receita);
    }

    public void removerMinhasReceitas(Receita receita) {
        this.minhasReceitas.remove(receita);
    }

    public List<Receita> getMinhasReceitas() {
        return minhasReceitas;
    }

    public void setMinhasReceitas(List<Receita> minhasReceitas) {
        this.minhasReceitas = minhasReceitas;
    }
}
