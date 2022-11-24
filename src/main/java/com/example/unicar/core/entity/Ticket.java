package com.example.unicar.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private LocalDateTime horaEntrada;

    private LocalDateTime horaSaida;

    private Integer valorTotal;

    private boolean valido;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_uuid")
    private Usuario usuario;

}
