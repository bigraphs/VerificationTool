package it.uniud.mads.jlibbig.proverif.exceptions;

public class WrongPolarityException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongPolarityException() {
    }

    public WrongPolarityException(String message) {
        super(message);
    }   
}
