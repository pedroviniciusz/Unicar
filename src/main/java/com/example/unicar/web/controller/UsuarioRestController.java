package com.example.unicar.web.controller;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends BaseRestController {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return writeResponseBody(service.cadastrar(usuario));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID uuid) {
        return writeResponseBody(service.findUsuarioByUuid(uuid));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return writeResponseBody(service.findAll());
    }
}
