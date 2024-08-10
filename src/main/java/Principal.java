import controle.ControleFactory;
import modelo.Usuario;
import controle.Controle;



public class Principal {
    public static void main(String[] args) {
        Controle controle = ControleFactory.criarControleGeral();

        Usuario pedro = controle.criarUsuario("pedro", "4315","@gmail", "31321");
        Usuario caio = controle.criarUsuario("caio", "4315","@gmail", "123141");
        Usuario joaquim = controle.criarUsuario("Joaquim", "senha", "@gmail","3214");

        Usuario caioc = controle.recuperarUsuarioPorId(1);
        Usuario pedror= controle.recuperarUsuarioPorId(2);
        Usuario joaquimm = controle.recuperarUsuarioPorId(3);


        System.out.println(caioc.getNome());
        System.out.println(pedror.getNome());
        System.out.println(joaquimm.getNome());

        Usuario ChefJoaquim = controle.criarUsuarioChef(joaquimm);



    }
}
