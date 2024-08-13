package controle;

import modelo.Receita;

public class ControleReceita {

    private final ControleRepositorioReceita controleRepositorioReceita;

    public ControleReceita(ControleRepositorioReceita controleRepositorioReceita) {
        this.controleRepositorioReceita = controleRepositorioReceita;
    }

    public Receita cadastrarReceita(Receita receita) {
        controleRepositorioReceita.salvarReceita(receita);
        return receita;
    }

}
