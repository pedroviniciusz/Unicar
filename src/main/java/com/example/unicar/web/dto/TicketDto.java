package com.example.unicar.web.dto;

import com.example.unicar.core.entity.Ticket;
import com.example.unicar.core.entity.Usuario;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class TicketDto {

    public TicketDto(Ticket ticket){
        this.uuid = ticket.getUuid();
        this.horaEntrada = ticket.getHoraEntrada();
        this.horaSaida = ticket.getHoraSaida();
        this.valorTotal = ticket.getValorTotal();
        this.valido = ticket.isValido();
        this.usuario = ticket.getUsuario().getUsername();
    }

    private final UUID uuid;

    private final LocalDateTime horaEntrada;

    private final LocalDateTime horaSaida;

    private final Integer valorTotal;

    private final boolean valido;

    private final String usuario;

    public static TicketDto transferToDto(Ticket ticket){
        return new TicketDto(ticket);
    }

    public static List<TicketDto> transferToDtoList(List<Ticket> tickets){
        return tickets.stream().map(TicketDto::new).collect(Collectors.toList());
    }

}
