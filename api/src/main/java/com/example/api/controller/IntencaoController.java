package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Intencao;
import com.example.api.service.IntencaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class IntencaoController {

    private final IntencaoService intencaoService;

    public IntencaoController(IntencaoService intencaoService){
        this.intencaoService = intencaoService;

    }
    @PostMapping()
    public Intencao salvarIntencao(@RequestBody Intencao intencao) {        
        return intencaoService.salvar(intencao);
    }

    @GetMapping()
    public Iterable<Intencao> listarIntencao() {
        return intencaoService.listarTodos();
    }
    
    
    
}
