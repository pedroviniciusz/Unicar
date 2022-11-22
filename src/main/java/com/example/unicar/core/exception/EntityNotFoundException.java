package com.example.unicar.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -5304966693588045124L;

    public EntityNotFoundException(String mensagem) {
        super(mensagem);
    }

}
