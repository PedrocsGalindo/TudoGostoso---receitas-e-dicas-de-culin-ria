package org.tudogostoso.exceptions;

public class ObjetoJaExiste extends Exception {
    public ObjetoJaExiste(String msg) {
        super(msg);
    }
    public ObjetoJaExiste(String msg, Throwable causa) {
        super(msg, causa);
    }
}
