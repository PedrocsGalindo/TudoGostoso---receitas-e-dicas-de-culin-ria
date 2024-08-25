package org.tudogostoso.repositorios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.tudogostoso.modelo.Usuario;
import org.tudogostoso.modelo.Ingrediente;
import org.tudogostoso.modelo.Receita;

public abstract class RepositorioGenerico <T> {


    //as subclasses podem ter acesso a path, assim mudando o caminho para cada repositorio
    protected Path path;

    public RepositorioGenerico(String filePath) {
        this.path = Paths.get(filePath);
    }
    public List<T> buscar(){

        List<T> objetos = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream( Files.newInputStream(this.path))){
            try{
                objetos = (List<T>) input.readObject();
            }catch (ClassNotFoundException e){
                System.out.println("erro ao ler arquivo na busca");
            }
        }catch (IOException e){
            System.out.println("Erro ao abrir arquivo na busca");
        }
        return objetos;
    }

    //salva usurio novo
    public void salvar(T objeto) {

        List<T> objetos = buscar();
        objetos.add(objeto);
        try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(this.path))){
            output.writeObject(objetos);
        }catch (IOException e){
            System.out.println("Erro ao abrir arquivo para salvar");
        }
    }

    public void salvarAtributo(T objeto) {
        String atributoASalvar = "";


        if (objeto instanceof Usuario) {
            Usuario usuario = (Usuario) objeto;
            atributoASalvar = String.valueOf(usuario.getId());
        } else if (objeto instanceof Receita) {
            Receita receita = (Receita) objeto;
            atributoASalvar = String.valueOf(receita.getId());
        } else if (objeto instanceof Ingrediente) {
            Ingrediente ingrediente = (Ingrediente) objeto;
            atributoASalvar = String.valueOf(ingrediente.getId());
        } else {
            throw new IllegalArgumentException("Tipo de objeto não suportado");
        }

        List<String> atributos = buscarAtributos();
        atributos.add(atributoASalvar);

        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(this.path))) {
            output.writeObject(atributos);
        } catch (IOException e) {
            System.out.println("Erro ao abrir arquivo para escrever");
        }
    }

    public List<String> buscarAtributos() {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(this.path))) {
            return (List<String>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao abrir arquivo para leitura");
            return new ArrayList<>();
        }
    }

    //remove usuario existente
    public void excluir(T objeto) {
        List<T> objetos = buscar();
        int hUsuarios = objetos.size();
        objetos.remove(objeto);

        //verefica se a lista foi alterada, se ela for, significa que existe o usuario e foi excluido
        if (objetos.size() != hUsuarios){
            try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(this.path))){
                output.writeObject(objetos);
            }catch (IOException e){
                System.out.println("Erro ao abrir arquivo para escrever");
            }
        }
    }

    //atualizar informações
    public void update(T objeto) {
        List<T> objetos = buscar();
        try{
            objetos.set(objetos.indexOf(objeto), objeto);
        }catch(IndexOutOfBoundsException e){
            System.out.println("objeto não existe no banco de dados");
        }
        try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(this.path))){
            output.writeObject(objetos);
        }catch (IOException e){
            System.out.println("Erro ao abrir arquivo para atualizar");
        }
    }
}
