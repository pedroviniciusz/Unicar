package com.example.unicar.web.controller;

import com.example.unicar.web.dto.UsuarioDto;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return writeResponseBody(service.cadastrar(usuario));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UsuarioDto> findByUuid(@PathVariable UUID uuid) {
        return writeResponseBody(UsuarioDto.transferToDto(service.findUsuarioByUuid(uuid)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return writeResponseBody(UsuarioDto.transferToDtoList(service.findAll()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteUsuarioByUuid(uuid);
        return writeResponseBody();
    }
}
