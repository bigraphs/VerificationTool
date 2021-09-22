package it.uniud.mads.jlibbig.proverif.exceptions;

public class NotFound404Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public NotFound404Exception() {
    }

    public NotFound404Exception(String message) {
        super(message);
    } 
}
