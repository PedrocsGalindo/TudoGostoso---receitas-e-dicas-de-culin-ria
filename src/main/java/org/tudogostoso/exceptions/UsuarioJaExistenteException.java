package org.tudogostoso.exceptions;

public class UsuarioJaExistenteException extends Exception {

    public UsuarioJaExistenteException(String msg) {
        super(msg);
    }
    public UsuarioJaExistenteException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
