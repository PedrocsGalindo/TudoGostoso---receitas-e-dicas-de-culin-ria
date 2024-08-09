import controle.ControleRepositorioUsuario;
import controle.ControleUsuario;
import modelo.Usuario;
import repositorio.RepositorioUsuario;


public class Principal {
    public static void main(String[] args) {
        ControleRepositorioUsuario controleRepositorioUsuario= new ControleRepositorioUsuario(new RepositorioUsuario());
        ControleUsuario controleU = new ControleUsuario(controleRepositorioUsuario);

        Usuario pedro = controleU.criarUsuario("pedro", "4315","pedrocgsouza23@gmail", "123141");
        Usuario caio = controleU.criarUsuario("caio", "4315","pedrocgsouza23@gmail", "123141");

        Usuario caioc = controleRepositorioUsuario.recuperarUsuarioPorId(1);
        Usuario pedror= controleRepositorioUsuario.recuperarUsuarioPorId(0);

        System.out.println(pedror.getNome());
        System.out.println(caioc.getNome());



    }
}
