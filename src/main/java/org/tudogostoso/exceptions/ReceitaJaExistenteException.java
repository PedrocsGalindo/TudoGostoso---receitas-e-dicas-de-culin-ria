package org.tudogostoso.exceptions;

public class ReceitaJaExistenteException extends Exception {
    public ReceitaJaExistenteException(String msg) {
        super(msg);
    }
    public ReceitaJaExistenteException(String msg, Throwable causa) {
        super(msg, causa);
    }
}

