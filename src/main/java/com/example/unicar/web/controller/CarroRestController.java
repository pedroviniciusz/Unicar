package com.example.unicar.web.controller;

import com.example.unicar.core.entity.Carro;
import com.example.unicar.core.service.CarroService;
import com.example.unicar.web.dto.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
public class CarroRestController extends BaseRestController {

    @Autowired
    private CarroService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CarroDto>> findAll() {
        return writeResponseBody(CarroDto.transferToDtoList(service.findAll()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<CarroDto> findByUuid(@PathVariable UUID uuid) {
        return writeResponseBody(CarroDto.transferToDto(service.findCarroByUuid(uuid)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/cadastrar")
    public ResponseEntity<Carro> create(@RequestBody Carro carro) {
        return writeResponseBody(service.create(carro));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        service.deleteByUuid(uuid);
        return writeResponseBody();
    }

}
