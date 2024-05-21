package br.imd.ufrn.sge.exceptions;

public class NomeNaoEncontradoException extends RuntimeException{
    public NomeNaoEncontradoException(String message) {
        super(message);
    }

    public NomeNaoEncontradoException() {
        super("Nenhuma pessoa com esse nome foi encontrada!");
    }
}