package it.uniud.mads.jlibbig.proverif.exceptions;

public class NoInstanceFound extends Exception {

    private static final long serialVersionUID = 1L;

    public NoInstanceFound() {
    }

    public NoInstanceFound(String message) {
        super(message);
    }
}
