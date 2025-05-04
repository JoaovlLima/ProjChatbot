package com.example.api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openapitools.inference.client.model.EmbeddingsList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.api.config.OpenAIConfig;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.embeddings.CreateEmbeddingResponse;
import com.openai.models.embeddings.EmbeddingCreateParams;

@Service
public class EmbeddingService {
    private OpenAIClient client;
    

    public EmbeddingService(OpenAIConfig openAIConfig){
        this.client = OpenAIOkHttpClient.builder()
               .apiKey(openAIConfig.getOpenAiKey())
               .build();
    }

    public List<Float> gerarEmbeddings(String texto){
        EmbeddingCreateParams params = EmbeddingCreateParams.builder()
              .model("text-embedding-3-small")
              .input(texto)
              .build();


        CreateEmbeddingResponse response = client.embeddings().create(params);

        List<Double> doubleList = response.data().get(0).embedding();

        return doubleList.stream().map(Double::floatValue).collect(Collectors.toList());

    }


}
