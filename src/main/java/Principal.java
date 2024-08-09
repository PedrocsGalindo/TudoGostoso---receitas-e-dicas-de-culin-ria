import controle.ControleRepositorioUsuario;
import controle.ControleUsuario;
import modelo.Usuario;


public class Principal {
    public static void main(String[] args) {

        Usuario pedro = ControleUsuario.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "123141");
        Usuario caio = ControleUsuario.criarUsuario("caio", "4315","pedrocgsouza23@gmail", "123141");

        Usuario caioc = ControleRepositorioUsuario.recuperarUsuarioPorId(1);
        Usuario pedror= ControleRepositorioUsuario.recuperarUsuarioPorId(0);

        System.out.println(pedror.getNome());
        System.out.println(caioc.getNome());



    }
}
