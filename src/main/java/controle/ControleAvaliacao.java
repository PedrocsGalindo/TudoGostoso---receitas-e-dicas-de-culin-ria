package controle;

import modelo.Avaliacao;
import repositorio.RepositorioAvaliacao;

import java.util.List;

public class ControleAvaliacao {
    private final RepositorioAvaliacao repositorio;

    public ControleAvaliacao(RepositorioAvaliacao repositorioAvaliacao){
        this.repositorio= repositorioAvaliacao;

    }
    public void salvar(Avaliacao avaliacao){
        repositorio.salvar(avaliacao);
    }
    public void excluir(Avaliacao avaliacao){
        repositorio.excluir(avaliacao);
    }
    public void update(Avaliacao avaliacao){
        repositorio.update(avaliacao);
    }
    public List<Avaliacao> buscar(){
        return repositorio.buscar();

    }
}

