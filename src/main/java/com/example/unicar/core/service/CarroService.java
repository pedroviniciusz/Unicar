package com.example.unicar.core.service;

import com.example.unicar.config.Messages;
import com.example.unicar.core.entity.Carro;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.unicar.config.Messages.NAO_EXISTE_CARRO_COM_ESTE_UUID;

@Service
@RequiredArgsConstructor
public class CarroService {

    @Autowired
    private CarroRepository repository;

    private final Messages messages;

    public Carro findCarroByUuid(UUID uuid) {
        Optional<Carro> carro = repository.findById(uuid);
        return carro.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_CARRO_COM_ESTE_UUID)));
    }

    public List<Carro> findAll(){
        return repository.findAll();
    }

    public Carro cadastrar(Carro carro){
        return repository.save(carro);
    }

}
