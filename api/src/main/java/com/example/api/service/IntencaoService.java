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



@Service
public class IntencaoService {
  private final Index intencaoIndex;
  private final EmbeddingService embeddingService;

  public IntencaoService(@Qualifier("intencaoIndex") Index intencaoIndex, EmbeddingService embeddingService) {
    this.intencaoIndex = intencaoIndex;
    this.embeddingService = embeddingService;
  }
  
  public void salvarIntencao(String texto){
    List<Float> embeddings = embeddingService.gerarEmbeddings(texto);
    
    Struct metadata = Struct.newBuilder()
    .putFields("texto", Value.newBuilder().setStringValue(texto).build())
    .build();

    intencaoIndex.upsert(UUID.randomUUID().toString(), embeddings,null,null, metadata,"default");

  }
}