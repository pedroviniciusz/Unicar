package com.example.unicar.core.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

@RequiredArgsConstructor
@Configuration
public class Messages {

    public static final String NAO_EXISTE_USUARIO_COM_ESTE_USERNAME = "nao.existe.usuario.cadastrado.com.este.username";
    public static final String JA_EXISTE_CADASTRO_COM_ESTE_USUARIO = "ja.existe.cadastro.com.este.usuario";
    public static final String JA_EXISTE_CADASTRO_COM_ESTE_CPF = "ja.existe.cadastro.com.este.cpf";
    public static final String CPF_INVALIDO = "cpf.invalido";
    public static final String A_SENHA_NAO_PODE_SER_NULA = "a.senha.nao.pode.ser.nula";
    public static final String NAO_EXISTE_CARRO_COM_ESTE_UUID = "nao.existe.carro.este.uuid";
    public static final String NAO_EXISTE_USUARIO_COM_ESTE_UUID = "nao.existe.usuario.este.uuid";
    public static final String NAO_EXISTE_TICKET_COM_ESTE_UUID = "nao.existe.ticket.com.este.uuid";
    public static final String NAO_EXISTE_TICKET_CADASTRADO_COM_ESTE_USUARIO = "nao.existe.ticket.cadastrado.com.este.usuario";
    public static final String JA_EXISTE_CARRO_CADASTRADO_COM_ESTA_PLACA = "ja.existe.carro.cadastrado.com.esta.placa";
    public static final String PLACA_INVALIDA = "placa.invalida";

    private final ResourceBundleMessageSource messageSource;

    public String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

}
