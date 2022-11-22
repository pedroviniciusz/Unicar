package com.example.unicar.web.dto;

import com.example.unicar.core.entity.Carro;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class CarroDto {

    public CarroDto(Carro carro) {
        this.uuid = carro.getUuid();
        this.montadora = carro.getMontadora();
        this.modelo = carro.getModelo();
        this.placa = carro.getPlaca();
        this.cor = carro.getCor();
        this.ano = carro.getAno();
        this.proprietario = UsuarioDto.transferToDto(carro.getProprietario());
    }

    private final UUID uuid;

    private final String montadora;

    private final String modelo;

    private final String placa;

    private final String cor;

    private final Date ano;

    private final UsuarioDto proprietario;

    public static CarroDto transferToDto(Carro carro){
        return new CarroDto(carro);
    }

    public static List<CarroDto> transferToDtoList(List<Carro> carros){
        return carros.stream().map(CarroDto::new).collect(Collectors.toList());
    }

}
