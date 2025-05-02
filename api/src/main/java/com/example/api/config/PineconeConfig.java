package com.example.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;


@Configuration
public class PineconeConfig {

    @Value("${pinecone.api.token}")
    private String apiToken;

    @Value("${pinecone.environment}")
    private String environment;

    @Value("${pinecone.name.intencao}")
    private String intencaoIndex;

    @Value("${pinecone.name.documentacao}")
    private String documentacaoIndex;


    @Bean
    public Pinecone pineconeClient(){
        return new Pinecone.Builder(apiToken).build();
    }
    
    @Bean(name = "intencaoIndex")
    public Index intencaoIndex(Pinecone pinencone){
        return pinencone.getIndexConnection(intencaoIndex);
    }
    @Bean(name = "documentacaoIndex")
    public Index documentacaoIndex(Pinecone pinecone){
        return pinecone.getIndexConnection(documentacaoIndex);
    }
 
    
}
