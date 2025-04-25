package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.api.model.Intencao;
import com.example.api.repository.IntencaoRepository;

@Service
public class IntencaoService {
    private final IntencaoRepository intencaoRepository;

    public IntencaoService(IntencaoRepository intencaoRepository) {
        this.intencaoRepository = intencaoRepository;
    }
    
    
    public Intencao salvar(Intencao intencao){
        return intencaoRepository.save(intencao);
    }
    public List<Intencao> listarTodos(){
        return  intencaoRepository.findAll();
    }
}
