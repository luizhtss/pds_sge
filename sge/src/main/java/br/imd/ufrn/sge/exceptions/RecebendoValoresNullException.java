package br.imd.ufrn.sge.exceptions;

public class RecebendoValoresNullException  extends RuntimeException{
    public RecebendoValoresNullException(String message) {
        super(message);
    }

    public RecebendoValoresNullException() {
        super("Não aceita valores nulos ou vazios");
    }
}