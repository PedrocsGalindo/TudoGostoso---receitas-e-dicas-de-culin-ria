import controle.ControleRepositorioUsuario;
import controle.Usuario;


public class Principal {
    public static void main(String[] args) {
        ControleRepositorioUsuario controle = new ControleRepositorioUsuario();

        Usuario pedro = controle.criarESalvarUsuario("pedro", "4315","pedrocg", "123141");
        Usuario caio = controle.criarESalvarUsuario("caio", "4315","pedrocg", "123141");

        Usuario caioc = controle.recuperarUsuarioPorId(1);
        Usuario pedror= controle.recuperarUsuarioPorId(0);

        System.out.println(pedror.getNome());
        System.out.println(caioc.getNome());



    }
}
