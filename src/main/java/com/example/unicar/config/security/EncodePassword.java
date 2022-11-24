package com.example.unicar.config.security;

import com.example.unicar.core.entity.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncodePassword {

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public void encodePassword(Usuario usuario){
        usuario.setPassword(encoder().encode(usuario.getPassword()));
    }

}
