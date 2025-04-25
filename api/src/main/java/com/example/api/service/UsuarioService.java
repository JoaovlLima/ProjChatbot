package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

public Usuario salvar(Usuario usuario){
    return usuarioRepository.save(usuario);
}

public List<Usuario> listarTodos(){
    return usuarioRepository.findAll();
}
    
    
}
