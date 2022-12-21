package com.example.unicar.core.service;

import com.example.unicar.config.Messages;
import com.example.unicar.core.entity.Carro;
import com.example.unicar.core.exception.CarroException;
import com.example.unicar.core.exception.EntityNotFoundException;
import com.example.unicar.core.repository.CarroRepository;
import com.example.unicar.core.util.PlacaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.unicar.config.Messages.*;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository repository;

    private final Messages messages;

    public Carro findCarroById(UUID uuid) {
        Optional<Carro> carro = repository.findById(uuid);
        return carro.orElseThrow(() -> new EntityNotFoundException(messages.getMessage(NAO_EXISTE_CARRO_COM_ESTE_UUID)));
    }

    public List<Carro> findAll(){
        return repository.findAll();
    }

    public Carro create(Carro carro){

        if(existsCarroByPlaca(PlacaUtil.formatarPlaca(carro.getPlaca()))){
            throw new CarroException(messages.getMessage(JA_EXISTE_CARRO_CADASTRADO_COM_ESTA_PLACA));
        }

        if(!PlacaUtil.isValida(carro.getPlaca())){
            throw new CarroException(messages.getMessage(PLACA_INVALIDA));
        }

        carro.setPlaca(PlacaUtil.formatarPlaca(carro.getPlaca()));

        return repository.save(carro);
    }

    public void deleteById(UUID uuid){

        if(!existsCarroById(uuid)){
            throw new EntityNotFoundException(messages.getMessage(NAO_EXISTE_CARRO_COM_ESTE_UUID));
        }

        repository.deleteById(uuid);
    }

    private boolean existsCarroById(UUID uuid){
        return repository.existsById(uuid);
    }

    private boolean existsCarroByPlaca(String placa){
        return repository.existsCarroByPlaca(placa);
    }

}
