package br.imd.ufrn.sge.exceptions;

public class IdJaExisteException extends RuntimeException{
    public IdJaExisteException(String message) {
        super(message);
    }
    public IdJaExisteException() {super("ID conflitante.");}
}
