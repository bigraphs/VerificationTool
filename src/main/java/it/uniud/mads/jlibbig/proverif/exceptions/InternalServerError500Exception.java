package it.uniud.mads.jlibbig.proverif.exceptions;

public class InternalServerError500Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public InternalServerError500Exception() {
    }

    public InternalServerError500Exception(String message) {
        super(message);
    }  
}
