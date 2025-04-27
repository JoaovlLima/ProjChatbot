package com.example.api.service;

import java.util.List;

import java.util.Optional;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.dto.UsuarioDTO;
import com.example.api.exception.UsuarioNaoEncontradoException;
import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

public Usuario salvar(Usuario usuario){
    if (usuario.getCpf().isEmpty()||usuario.getNome().isEmpty()||usuario.getSenha().isEmpty()){
        throw new IllegalIdentifierException("Algum campo vazio");
    }
    return usuarioRepository.save(usuario);
}

public List<Usuario> listarTodos(){
    return usuarioRepository.findAll();
}
public UsuarioDTO buscar(String cpf){
    if (cpf == ""|| cpf == null || cpf.isEmpty()){
        throw new IllegalIdentifierException("CPF não pode ser vazio");

    }
    Usuario usuario = usuarioRepository.findByCpf(cpf)
                 .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario Não Encontrado Com o cpf: "+cpf));

    return new UsuarioDTO(usuario.getNome(), usuario.getCpf());
    
    
}



}
