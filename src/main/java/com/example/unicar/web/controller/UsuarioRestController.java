package com.example.unicar.web.controller;

import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends BaseRestController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public  ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        Usuario usuario = service.findById(id);
        return writeResponseBody(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return writeResponseBody(list);
    }

}
