package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Menssagem;

public interface MenssagemRepository extends JpaRepository<Menssagem, Long> {
    
}
