package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long>{
    
}
