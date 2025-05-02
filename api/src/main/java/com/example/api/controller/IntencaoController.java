package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api.config.PineconeConfig;
import com.example.api.model.Intencao;
import com.example.api.service.IntencaoService;

import io.pinecone.clients.Pinecone;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/intencoes")
public class IntencaoController {

    private final IntencaoService intencaoService;
    
   @Autowired
   public IntencaoController(IntencaoService intencaoService){
    this.intencaoService = intencaoService;
   }
  
   @PostMapping()
   public String salvarIntencoes(@RequestBody String texto) {

       intencaoService.salvarIntencao(texto); 
       return "Intencão "+texto+" Salva Com sucesso";
   }
   
    
}
