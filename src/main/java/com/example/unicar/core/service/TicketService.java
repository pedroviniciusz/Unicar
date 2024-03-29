package com.example.unicar.core.service;

import com.example.unicar.core.message.Messages;
import com.example.unicar.core.entity.Ticket;
import com.example.unicar.core.entity.Usuario;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.unicar.core.message.Messages.NAO_EXISTE_TICKET_CADASTRADO_COM_ESTE_USUARIO;
import static com.example.unicar.core.message.Messages.NAO_EXISTE_TICKET_COM_ESTE_UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;

    private final Messages messages;

    public Ticket findTicketById(UUID uuid) {
        Optional<Ticket> ticket = repository.findById(uuid);
        return ticket.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_TICKET_COM_ESTE_UUID)));
    }

    public List<Ticket> findAllByUsuarioUuid(UUID uuid){
        return repository.findAllByUsuarioUuidAndExcluidoFalse(uuid);
    }

    public List<Ticket> findAll(){
        return repository.findAll();
    }

    public Ticket findTopByUsuarioUuid(UUID uuid){
        Optional<Ticket> ticket = repository.findTopByUsuarioUuidAndValidoTrueOrderByInclusaoDesc(uuid);
        return ticket.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_TICKET_CADASTRADO_COM_ESTE_USUARIO)));
    }

    public void checkIn(Usuario usuario){

        Ticket ticket = new Ticket();

        ticket.setHoraEntrada(LocalDateTime.now());
        ticket.setHoraSaida(null);
        ticket.setValorTotal(null);
        ticket.setValido(true);
        ticket.setUsuario(usuario);

        repository.save(ticket);
    }

    public void checkOut(Usuario usuario){

        Ticket ticket = findTopByUsuarioUuid((usuario.getUuid()));

        ticket.setHoraSaida(LocalDateTime.now());
        ticket.setValorTotal(calculateValue(ticket));
        ticket.setValido(false);

        repository.save(ticket);

    }

    private long calculateValue(Ticket ticket){

        long horaTotal = ChronoUnit.HOURS.between(ticket.getHoraEntrada(), ticket.getHoraSaida());

        return horaTotal * BigDecimal.valueOf(5).intValue();

    }

}
