package org.tudogostoso.modelo;

public class Sessao {

    private static Usuario usuarioSessao;
    private static Receita receitaSessao;
    private static String ultimaCena;

    public static Usuario getUsuarioSessao() {
        return usuarioSessao;
    }
    public static void setUsuarioSessao(Usuario novoUsuarioSessao) {
        usuarioSessao = novoUsuarioSessao;
    }
    public static Receita getReceitaSessao() {
        return receitaSessao;
    }
    public static void setReceitaSessao(Receita novaReceitaSessao) {
        receitaSessao = novaReceitaSessao;
    }
    public static String getUltimaCena() {
        return ultimaCena;
    }
    public static void setUltimaCena(String ultimaCena) {
        Sessao.ultimaCena = ultimaCena;
    }

}
