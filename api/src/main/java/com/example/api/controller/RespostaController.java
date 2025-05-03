package com.example.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.RespostaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/resposta")
public class RespostaController {
    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService){
        this.respostaService = respostaService;
    }

   
    
    
    
}
