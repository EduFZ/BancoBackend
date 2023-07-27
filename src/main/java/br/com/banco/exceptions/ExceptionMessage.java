package br.com.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionMessage extends RuntimeException{

    public ExceptionMessage(String message) {
        super(message);
    }

}
