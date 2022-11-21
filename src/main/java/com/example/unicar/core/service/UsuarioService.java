package com.example.unicar.core.service;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar este usuário"));
    }

}
