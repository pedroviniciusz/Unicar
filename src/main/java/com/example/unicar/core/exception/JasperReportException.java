package com.example.unicar.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JasperReportException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1525196321541405042L;

    public JasperReportException(String mensagem) {
        super(mensagem);
    }

}
