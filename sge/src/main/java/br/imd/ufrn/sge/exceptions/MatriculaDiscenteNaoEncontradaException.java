package br.imd.ufrn.sge.exceptions;

public class MatriculaDiscenteNaoEncontradaException extends RuntimeException{
    public MatriculaDiscenteNaoEncontradaException(String message) {
        super(message);
    }

    public MatriculaDiscenteNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
