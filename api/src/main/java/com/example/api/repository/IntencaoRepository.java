package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Intencao;



public interface IntencaoRepository extends JpaRepository<Intencao,Long> {
    
}
