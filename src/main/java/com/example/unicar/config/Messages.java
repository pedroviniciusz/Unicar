package com.example.unicar.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@RequiredArgsConstructor
@Configuration
public class Messages {

    private final ResourceBundleMessageSource messageSource;

    public static final String NAO_EXISTE_USUARIO_COM_ESTE_USERNAME = "nao.existe.usuario.cadastrado.com.este.username";
    public static final String JA_EXISTE_CADASTRO_COM_ESTE_USUARIO = "ja.existe.cadastro.com.este.usuario";
    public static final String A_SENHA_NAO_PODE_SER_NULA = "a.senha.nao.pode.ser.nula";
    public static final String NAO_EXISTE_CARRO_COM_ESTE_UUID = "nao.existe.carro.este.uuid";
    public static final String NAO_EXISTE_USUARIO_COM_ESTE_UUID = "nao.existe.usuario.este.uuid";
    public static final String NAO_EXISTE_TICKET_COM_ESTE_UUID = "nao.existe.ticket.com.este.uuid";
    public static final String NAO_EXISTE_TICKET_CADASTRADO_COM_ESTE_USUARIO = "nao.existe.ticket.cadastrado.com.este.usuario";


    public String getMessage(String code){
        Locale locale = LocaleContextHolder.getLocale();
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource.getMessage(code, null, locale);
    }

}
