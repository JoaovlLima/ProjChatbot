package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
}
