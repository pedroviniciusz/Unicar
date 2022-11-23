package com.example.unicar.core.repository;

import com.example.unicar.core.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

}
