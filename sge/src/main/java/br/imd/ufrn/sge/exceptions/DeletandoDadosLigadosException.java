package br.imd.ufrn.sge.exceptions;

public class DeletandoDadosLigadosException extends RuntimeException{
    public DeletandoDadosLigadosException (String message) {
        super(message);
    }
    public DeletandoDadosLigadosException () {super("Os Dados que esta tentando deletar estao ligados a um tipo de usuario");}
}