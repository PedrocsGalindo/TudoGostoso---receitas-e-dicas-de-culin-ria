package repositorio;

import controle.ControleReceita;
import controle.ControleRepositorioReceita;
import modelo.*;
import controle.ControleUsuario;
import controle.ControleRepositorioUsuario;
import exceptions.UsuarioJaExistenteException;

import javax.mail.internet.InternetAddress;
 public class Mainteste {

  public static void main(String[]arg){

   RepositorioIngrediente repositorioIngrediente = new RepositorioIngrediente();
   Ingrediente a1 = new Ingrediente("banana");
    ItemIngrediente a = new ItemIngrediente(a1,4,UnidadeMedida.GRAMAS);


    repositorioIngrediente.salvar(a1);
    repositorioIngrediente.buscarIngredientePorNome("banana");
    System.out.println(repositorioIngrediente.buscarIngredientePorNome("banana"));
    System.out.println(a);
  }
 }





