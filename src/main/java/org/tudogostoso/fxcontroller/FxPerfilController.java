package org.tudogostoso.fxcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tudogostoso.controle.ControleReceita;
import org.tudogostoso.controle.ControleUsuario;
import org.tudogostoso.modelo.Sessao;
import org.tudogostoso.modelo.Usuario;
import org.tudogostoso.repositorios.RepositorioReceitas;
import org.tudogostoso.repositorios.RepositorioUsuarios;

public class FxPerfilController {

    @FXML
    private Button BTNListaDeCompras;

    @FXML
    private Button BTNMinhasReceitasFavoritas;

    @FXML
    private Button BTNVoltar;

    @FXML
    private Label LabelD;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelNomeDeUsuario;

    //Serve para instânciar o gerenciador de Telas
    private FxGerenciadorTelas gerenciadorTelas = FxGerenciadorTelas.getInstance();


    @FXML
    public void initialize() {
// Serve para saber o usuário da sessão
        Usuario usuario = Sessao.getUsuarioSessao();


        LabelNomeDeUsuario.setText(usuario.getNome());
        LabelD.setText(String.valueOf(usuario.getId()));
        LabelEmail.setText(String.valueOf(usuario.getEmail()));
    }

    //Método do para ir na lista de Compras

    @FXML
    private void telaListaDeCompras(ActionEvent event ){
        //tem que adicionar o nome correto da tela, quando for feita
        gerenciadorTelas.mudarTela("ListaDeCompras",event);
    }
//Fazer a tela e colocar o mesmo nome
    @FXML
    private void telaMinhasReceitaFavoritas(ActionEvent event){
        gerenciadorTelas.mudarTela("MinhasReceitasFavoritas",event);
    }

    //Método para voltar na tela anterior, talvez seja possível mapear a tela anterior para facilitar
    @FXML
    private void voltar(ActionEvent event){
        gerenciadorTelas.mudarTela("TelaAnterior",event);
    }



}
