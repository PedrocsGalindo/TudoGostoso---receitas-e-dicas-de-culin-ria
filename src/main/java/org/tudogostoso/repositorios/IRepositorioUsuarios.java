package org.tudogostoso.repositorios;

import org.tudogostoso.modelo.Usuario;

import javax.mail.internet.InternetAddress;

public interface IRepositorioUsuarios {
    int getLastId();

    //recuperar Usuario com id, se não achar retorna NULL
    Usuario buscarPorId(int id);

    //recuperar Usuario com cpf, se não achar retorna NULL
    Usuario buscarPorCpf(String cpf);

    //recuperar Usuario com emaill, se não achar retorna NULL
    Usuario buscarPorEmail(InternetAddress email);

    void salvar(Usuario usuario);
    void excluir(Usuario usuario);
    void update(Usuario usuario);

}
