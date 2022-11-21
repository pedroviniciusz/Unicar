package com.example.unicar.web.controller;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends BaseRestController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return writeResponseBody(service.cadastrar(usuario));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        return writeResponseBody(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return writeResponseBody(service.findAll());
    }

}
