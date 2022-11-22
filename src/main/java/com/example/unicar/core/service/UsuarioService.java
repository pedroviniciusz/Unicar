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
import java.util.UUID;

import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public Usuario findUsuarioByUuid(UUID uuid) {
        Optional<Usuario> usuario = repository.findUsuarioByUuid(uuid);
        return usuario.orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar este usuário"));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario cadastrar(Usuario usuario){
        
        if(existeUsuario(usuario.getUsername())){
            throw new RuntimeException("Já existe cadastro com este usuário");
        }

        codificarSenha(usuario);

        Usuario usuarioCriado = repository.save(usuario);

        return usuarioCriado;
        
    }

    private boolean existeUsuario(String username){
        boolean existeUsuario;
        return existeUsuario = repository.existsUsuarioByUsername(username);
    }

    private void codificarSenha(Usuario usuario){

        if(isNullOrEmpty(usuario.getPassword())){
            throw new IllegalArgumentException("A senha não pode ser nula");
        }

        usuario.setPassword(encoder().encode(usuario.getPassword()));

    }

}
