package com.example.api.service;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.stereotype.Service;

import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class IntencaoService {
  private final Index intencaoIndex;
  private final EmbeddingService embeddingService;

  public IntencaoService(@Qualifier("intencaoIndex") Index intencaoIndex, EmbeddingService embeddingService) {
    this.intencaoIndex = intencaoIndex;
    this.embeddingService = embeddingService;
  }
  
  public void salvarIntencao(String texto){
    String textoLimpo = texto;

 // Try catch para transformar o json em texto
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree(texto);

      if (node.has("texto")){
        textoLimpo = node.get("texto").asText();
      }
    } catch (Exception e) {
      
    }
  // Mando para o EmbeddingService para transformar o texto em vetores
    List<Float> embeddings = embeddingService.gerarEmbeddings(textoLimpo);
    
    //Formato metadata para alocar no banco vetorial
    Struct metadata = Struct.newBuilder()
    .putFields("texto", Value.newBuilder().setStringValue(textoLimpo).build())
    .build();

    intencaoIndex.upsert(UUID.randomUUID().toString(), embeddings,null,null, metadata,"default");

  }
}