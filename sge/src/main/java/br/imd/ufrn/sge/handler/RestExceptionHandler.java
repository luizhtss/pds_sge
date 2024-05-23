package br.imd.ufrn.sge.handler;

import br.imd.ufrn.sge.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdJaExisteException.class)
    private ResponseEntity<String> IdJaExisteHandler(IdJaExisteException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    private ResponseEntity<String> IdNaoEncontradoHandler(IdNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(NomeNaoEncontradoException.class)
    private ResponseEntity<String> NomeNaoEncontradoException(NomeNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(DeletandoDadosLigadosException.class)
    private ResponseEntity<String> DeletandoDadosLigadosException(DeletandoDadosLigadosException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
    @ExceptionHandler(RecebendoValoresNullException.class)
    private ResponseEntity<String> RecebendoValoresNullException(RecebendoValoresNullException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }


}
