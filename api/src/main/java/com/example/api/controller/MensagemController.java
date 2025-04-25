package com.example.api.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Menssagem;
import com.example.api.service.MensagemService;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService){
        this.mensagemService = mensagemService;
    }
    @PostMapping
    public Menssagem salvarMenssagem(@RequestBody Menssagem menssagem) {
        
        return mensagemService.salvar(menssagem);
    }
   
    
}
