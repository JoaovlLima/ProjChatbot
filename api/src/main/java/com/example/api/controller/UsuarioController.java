package com.example.api.controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UsuarioDTO;
import com.example.api.model.Usuario;
import com.example.api.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  private final UsuarioService usuarioService;
    
  public UsuarioController(UsuarioService usuarioService){
    this.usuarioService = usuarioService;
  }

  @PostMapping()
  public Usuario salvarUsuario(@RequestBody Usuario usuario){
      
      return usuarioService.salvar(usuario);
  }

  @GetMapping()
  public Iterable<Usuario> listarUsuario() {
      return usuarioService.listarTodos();
  }
  @GetMapping("/{cpf}")
  public UsuarioDTO buscarUsuario(@PathVariable String cpf) {

      return usuarioService.buscar(cpf);
  }


  
  
}
