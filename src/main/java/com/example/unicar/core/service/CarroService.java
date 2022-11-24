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

    private final CarroRepository repository;

    private final Messages messages;

    public Carro findCarroByUuid(UUID uuid) {
        Optional<Carro> carro = repository.findById(uuid);
        return carro.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_CARRO_COM_ESTE_UUID)));
    }

    public List<Carro> findAll(){
        return repository.findAll();
    }

    public Carro create(Carro carro){
        return repository.save(carro);
    }

    public void deleteByUuid(UUID uuid){

        if(!existsCarroByUuid(uuid)){
            throw new EntityNotFoundException(messages.getMessage(NAO_EXISTE_CARRO_COM_ESTE_UUID));
        }

        repository.deleteById(uuid);
    }

    private boolean existsCarroByUuid(UUID uuid){
        return repository.existsById(uuid);
    }

}
