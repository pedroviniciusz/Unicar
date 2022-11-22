package com.example.unicar.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityDuplicateException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -4553690301409800293L;

    public EntityDuplicateException(String mensagem) {
        super(mensagem);
    }

}
