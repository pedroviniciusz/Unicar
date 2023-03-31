package com.example.unicar.core.repository;

import com.example.unicar.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findUsuarioByUsername(String username);

    boolean existsUsuarioByUsername(String username);

    boolean existsUsuarioByCpf(String cpf);

}
