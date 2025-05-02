package com.example.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.api.config.OpenAIConfig;

@Service
public class EmbeddingService {
    private final WebClient webClient;
    private final OpenAIConfig openAIConfig;

    public EmbeddingService(OpenAIConfig openAIConfig){
        this.openAIConfig = openAIConfig;
        this.webClient = WebClient.builder()
                     .baseUrl(openAIConfig.getOpenAiUrl())
                     .defaultHeader("Authorization", "Bearer "+openAIConfig.getOpenAiKey())
                     .build();
    }

    public List<Float> gerarEmbeddings(String texto){
        Map<String, Object> body = Map.of("model", "text-embedding-3-small",
                                            "input",texto
        );

        Map<String, Object> response = webClient.post()
                       .contentType(MediaType.APPLICATION_JSON)
                       .bodyValue(body)
                       .retrieve()
                       .bodyToMono(Map.class)
                       .block();

        List<Double> embeddingsList = (List<Double>)
            ((Map<String, Object>) ((List<Object>) response.get("data")).get(0)).get("embedding");

        return embeddingsList.stream().map(Double::floatValue).toList();
        
    }


}
