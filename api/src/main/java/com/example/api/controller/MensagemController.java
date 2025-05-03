package com.example.api.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.ChatService;
import com.example.api.service.MensagemService;
import com.example.api.service.RespostaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    private final MensagemService mensagemService;
    private final RespostaService respostaService;
    private final ChatService chatService;

    public MensagemController(MensagemService mensagemService, RespostaService respostaService, ChatService chatService){
        this.mensagemService = mensagemService;
        this.respostaService = respostaService;
        this.chatService = chatService;
    }
   @PostMapping()

   public String buscarIntencao(@RequestBody String mensagem) {
       String intencao = mensagemService.buscarIntencao(mensagem);
       String respostaSql = respostaService.buscarResposta(intencao);
       String respostaFinal = chatService.gerarResposta(mensagem, intencao, respostaSql);

       return respostaFinal;
       
   }
   
   
    
}
