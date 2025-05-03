package com.example.api.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import io.pinecone.clients.Index;

import java.util.List;

@Service
public class MensagemService {
private final Index intencaoIndex;
private final EmbeddingService embeddingService;

public MensagemService(@Qualifier("intencaoIndex") Index intencaoIndex, EmbeddingService embeddingService ){
    this.intencaoIndex = intencaoIndex;
    this.embeddingService = embeddingService;
}

public String buscarIntencao(String mensagem){
    List<Float> embeddingsConsulta = embeddingService.gerarEmbeddings(mensagem);
    
    var intencao = intencaoIndex.query(2, embeddingsConsulta,null,null,null,"default",null,false,true);
    
    if(!intencao.getMatchesList().isEmpty()){
        
        var match = intencao.getMatches(0);
        var metadata = match.getMetadata();

        if (metadata.containsFields("texto")){
            String textoIntencao = metadata.getFieldsOrThrow("texto").getStringValue();
            float score = match.getScore();
            return textoIntencao;
                }
    }
    return "Nenhum resultado encontrado";
}


    
}
