package com.example.unicar.web.controller;

import com.example.unicar.core.entity.Ticket;
import com.example.unicar.core.service.TicketService;
import com.example.unicar.web.dto.TicketDto;
import com.example.unicar.web.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketRestController extends BaseRestController{

    private final TicketService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<TicketDto> findByUuid(@PathVariable UUID uuid) {
        return writeResponseBody(TicketDto.transferToDto(service.findTicketByUuid(uuid)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<TicketDto>> findAll() {
        return writeResponseBody(TicketDto.transferToDtoList(service.findAll()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "usuario/{uuid}")
    public ResponseEntity<List<TicketDto>> findAllByUsuarioUuid(@PathVariable UUID uuid) {
        return writeResponseBody(TicketDto.transferToDtoList(service.findAllByUsuarioUuid(uuid)));
    }

}
