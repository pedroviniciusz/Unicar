package com.example.unicar.web.dto;

import com.example.unicar.core.entity.Carro;
import com.example.unicar.core.entity.Usuario;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class UsuarioDto {

    public UsuarioDto(Usuario usuario){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento();
        this.carros = usuario.getCarros();
    }

    private final UUID uuid;

    private final String username;

    private final String nome;

    private final String cpf;

    private final Date dataNascimento;

    private final List<Carro> carros;

    public static UsuarioDto transferToDto(Usuario usuario){
        return new UsuarioDto(usuario);
    }

    public static List<UsuarioDto> transferToDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

}
