package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Mensagem;

public interface MenssagemRepository extends JpaRepository<Mensagem, Long> {
    
}
