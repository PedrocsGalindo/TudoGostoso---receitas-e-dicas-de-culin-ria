import controle.ControleFactory;
import modelo.*;
import controle.Controle;

import java.util.List;


public class Principal {
    public static void main(String[] args) {
        Controle controle = ControleFactory.criarControleGeral();

        Usuario pedro = controle.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "31321");
        Usuario caio = controle.criarUsuario("caio", "4315","pedrocgsouza4fc@gmail", "123141");
        Usuario joaquim = controle.criarUsuario("Joaquim", "senha", "cesarpedrog23@gmail","3214");

        Usuario caioc = controle.recuperarUsuarioPorId(2);
        Usuario pedror= controle.recuperarUsuarioPorId(1);
        Usuario joaquimm = controle.recuperarUsuarioPorId(3);

        controle.criarIngrediente("cenoura");
        controle.criarIngrediente("batata");

        Ingrediente cenoura = controle.buscarIngredientePorNome("cenoura");
        Ingrediente batata = controle.buscarIngredientePorNome("batata");

        ItemIngrediente itemCenoura = controle.criarItemIngrediente(cenoura, 200, UnidadeMedida.GRAMAS);
        ItemIngrediente itemBatata = controle.criarItemIngrediente(batata, 300, UnidadeMedida.GRAMAS);
        List<ItemIngrediente> ingredientes = List.of(itemCenoura, itemBatata);

        Usuario joaquimChef = controle.criarUsuarioChef(joaquimm);

        List<String> preparo = List.of("bater", "juntar tudo");

        controle.criarReceita("bolo", joaquimChef, ingredientes, preparo, "assar", "2min", "larica");

        List<Receita> receitasJoaquim = controle.buscarReceitaPorAutor(joaquimChef);
        for(Receita r : receitasJoaquim){
            System.out.println(r.getTitulo());
        }

        Receita boloJoaquimChef = controle.buscarReceitaPorAutorETitulo(joaquimChef,"bolo");
        controle.addReceitafav(pedror,boloJoaquimChef);

        List<Receita> favPedro = pedror.getReceitasFav();
        for(Receita r : favPedro){
            System.out.println(r.getTitulo());
        }
    }
}
