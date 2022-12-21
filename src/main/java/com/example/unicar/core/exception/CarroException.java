package com.example.unicar.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarroException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -8384479781765506251L;

    public CarroException(String mensagem) {
        super(mensagem);
    }

}
