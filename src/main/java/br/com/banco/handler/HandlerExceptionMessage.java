package br.com.banco.handler;

import br.com.banco.exceptions.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionMessage {

    @ExceptionHandler(ExceptionMessage.class)
    public ResponseEntity<String> excessaoHandler(ExceptionMessage ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
