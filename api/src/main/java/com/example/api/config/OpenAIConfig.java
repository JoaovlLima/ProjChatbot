package com.example.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAIConfig {
    
    @Value("${openai.api.key}")
    private String openAiKey;

    @Value("${openai.api.url}")
    private String openAiUrl;

    public String getOpenAiKey() {
        return openAiKey;
    }

    public String getOpenAiUrl() {
        return openAiUrl;
    }

    
}
