package com.example.unicar.core.service;

import com.example.unicar.config.Messages;
import com.example.unicar.core.entity.Ticket;
import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.unicar.config.Messages.NAO_EXISTE_TICKET_COM_ESTE_UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;

    private final Messages messages;

    public Ticket findTicketByUuid(UUID uuid) {
        Optional<Ticket> ticket = repository.findById(uuid);
        return ticket.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_TICKET_COM_ESTE_UUID)));
    }

    public List<Ticket> findAllByUsuarioUuid(UUID uuid){
        return repository.findAllByUsuarioUuid(uuid);
    }

    public List<Ticket> findAll(){
        return repository.findAll();
    }

    public void createTicket(Usuario usuario){

        Ticket ticket = new Ticket();

        ticket.setHoraEntrada(LocalDateTime.now());
        ticket.setHoraSaida(null);
        ticket.setValorTotal(null);
        ticket.setValido(true);
        ticket.setUsuario(usuario);

        repository.save(ticket);
    }

}
