import controle.ControleFactory;
import modelo.Usuario;
import controle.Controle;



public class Principal {
    public static void main(String[] args) {
        Controle controle = ControleFactory.criarControleGeral();

        Usuario pedro = controle.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "123141");
        Usuario caio = controle.criarUsuario("caio", "4315","pedrocgsouza23@gmail", "123141");

        Usuario caioc = controle.recuperarUsuarioPorId(1);
        Usuario pedror= controle.recuperarUsuarioPorId(0);

        System.out.println(pedror.getNome());
        System.out.println(caioc.getNome());



    }
}
