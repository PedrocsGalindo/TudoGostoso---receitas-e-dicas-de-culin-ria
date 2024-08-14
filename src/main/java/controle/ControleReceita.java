package controle;

import exceptions.ReceitaJaExistenteException;
import modelo.Receita;

public class ControleReceita {

    private final ControleRepositorioReceita controleRepositorioReceita;

    public ControleReceita(ControleRepositorioReceita controleRepositorioReceita) {
        this.controleRepositorioReceita = controleRepositorioReceita;
    }

    public Receita cadastrarReceita(Receita receita) throws ReceitaJaExistenteException{
        if(controleRepositorioReceita.buscarReceitaPorAutorETitulo(receita.getAutor(), receita.getTitulo()) == null) {
            controleRepositorioReceita.salvarReceita(receita);
        }else {
            throw new ReceitaJaExistenteException("Usuario "+ receita.getAutor().getNome() + " já possui receita com o titulo " + receita.getTitulo());
        }
        return receita;
    }

    public int getLastId(){
        return controleRepositorioReceita.getLastId();
    }

}
