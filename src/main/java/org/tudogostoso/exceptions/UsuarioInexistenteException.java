package org.tudogostoso.exceptions;

public class UsuarioInexistenteException extends Exception{
    public UsuarioInexistenteException(String msg){
        super(msg);
    }
    public UsuarioInexistenteException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
