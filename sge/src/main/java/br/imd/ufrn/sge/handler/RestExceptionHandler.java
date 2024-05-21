package br.imd.ufrn.sge.handler;

import br.imd.ufrn.sge.exceptions.IdJaExisteException;
import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
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



}
