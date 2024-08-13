package controle;

import modelo.Avaliacao;
import repositorio.RepositorioAvaliacao;

import java.util.List;

public class ControleRepositorioAvaliacao {

    private final RepositorioAvaliacao controleRepositorioAvaliacao;

    public ControleRepositorioAvaliacao(RepositorioAvaliacao repositorioAvaliacao){
        this.controleRepositorioAvaliacao= repositorioAvaliacao;

    }
    public void salvar(Avaliacao avaliacao){
        controleRepositorioAvaliacao.salvar(avaliacao);
    }
    public void excluir(Avaliacao avaliacao){
        controleRepositorioAvaliacao.excluir(avaliacao);
    }
    public void update(Avaliacao avaliacao){
        controleRepositorioAvaliacao.update(avaliacao);
    }
    public List<Avaliacao> buscar(){
        return controleRepositorioAvaliacao.buscar();

    }


}
