package com.example.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.api.model.Mensagem;
import com.example.api.repository.MenssagemRepository;
import java.util.List;

@Service
public class MensagemService {

    private final MenssagemRepository menssagemRepository;

    public MensagemService(MenssagemRepository menssagemRepository) {
        this.menssagemRepository = menssagemRepository;
    }
  
   public Mensagem salvar(Mensagem menssagem){
    return menssagemRepository.save(menssagem);

   }
   public List<Mensagem> listarTodos(){
    return menssagemRepository.findAll();
   } 
   
}
