package br.imd.ufrn.sge.exceptions;

import org.springframework.http.HttpStatus;

public class AuthException extends Exception{


    private HttpStatus status;

    public AuthException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public AuthException(String message){
        super(message);
    }

    public AuthException(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus getStatus() {
        return status;
    }

}
