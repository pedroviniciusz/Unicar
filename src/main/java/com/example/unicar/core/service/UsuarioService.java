package com.example.unicar.core.service;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar este usuário"));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario cadastrar(Usuario usuario){
        
        if(existeUsuario(usuario.getEmail())){
            throw new RuntimeException("Já existe usuário com este e-mail");
        }

        codificarSenha(usuario);

        Usuario usuarioCriado = repository.save(usuario);

        return usuarioCriado;
        
    }

    private boolean existeUsuario(String email){
        boolean existeUsuario;
        return existeUsuario = repository.existsUsuarioByEmail(email);
    }

    private void codificarSenha(Usuario usuario){

        usuario.setSenha(encoder().encode(usuario.getSenha()));

    }

}
