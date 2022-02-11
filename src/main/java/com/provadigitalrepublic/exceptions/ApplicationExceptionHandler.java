package com.provadigitalrepublic.exceptions;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity userException(Exception e) {
        return new ResponseEntity("Usuário inválido ou ja cadastrado", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserErrosException.class)
    public ResponseEntity userErrorException(Exception e) {
        return new ResponseEntity("Usuário não existe", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValueException.class)
    public ResponseEntity valueException(Exception e) {
        return new ResponseEntity("Saldo insuficiente", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidValue.class)
    public ResponseEntity InvalidValue(Exception e) {
        return new ResponseEntity("Valor inválido", HttpStatus.BAD_REQUEST);
    }
}
