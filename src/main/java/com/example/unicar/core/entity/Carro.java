package com.example.unicar.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor
@SQLDelete(sql = "UPDATE carro SET excluido = true WHERE uuid=?")
@Entity
@Table(name = "carro")
public class Carro extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String montadora;

    private String modelo;

    private String placa;

    private String cor;

    private LocalDate fabricacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "proprietario_uuid")
    private Usuario proprietario;

}
