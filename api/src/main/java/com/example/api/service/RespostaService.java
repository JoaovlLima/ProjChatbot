package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.api.model.Resposta;
import com.example.api.repository.RespostaRepository;

@Service
public class RespostaService {
    private final RespostaRepository respostaRepository;

    public RespostaService(RespostaRepository respostaRepository){
        this.respostaRepository = respostaRepository;
    }

   
    public Resposta salvar(Resposta resposta){
        return respostaRepository.save(resposta);
        
    }
    public List<Resposta> listarTodos(){
        return respostaRepository.findAll();
    }
}
