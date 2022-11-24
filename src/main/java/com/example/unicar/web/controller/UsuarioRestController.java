package com.example.unicar.web.controller;

import com.example.unicar.web.dto.UsuarioDto;
import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends BaseRestController {

    private final UsuarioService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UsuarioDto> findByUuid(@PathVariable UUID uuid) {
        return writeResponseBody(UsuarioDto.transferToDto(service.findUsuarioByUuid(uuid)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UsuarioDto> findByUsuarioUsername(@PathVariable String username) {
        return writeResponseBody(UsuarioDto.transferToDto(service.findUsuarioByUsername(username)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return writeResponseBody(UsuarioDto.transferToDtoList(service.findAll()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return writeResponseBodyCreated(service.create(usuario));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/create/ticket")
    public ResponseEntity<Usuario> createTicket(@RequestBody Usuario usuario) {
        return writeResponseBodyCreated(service.createTicket(usuario.getUuid()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteUsuarioByUuid(uuid);
        return writeResponseBody();
    }
}
