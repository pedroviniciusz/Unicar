package com.example.unicar.core.repository;

import com.example.unicar.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findUsuarioByUsernameAndExcluidoFalse(String username);

    boolean existsUsuarioByUsernameAndExcluidoFalse(String username);

    boolean existsUsuarioByCpf(String cpf);

}
