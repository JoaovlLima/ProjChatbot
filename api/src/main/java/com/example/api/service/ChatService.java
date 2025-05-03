    package com.example.api.service;

    import java.util.List;
    import java.util.Map;

    import org.springframework.http.MediaType;
    import org.springframework.stereotype.Service;
    import org.springframework.web.reactive.function.client.WebClient;

    import com.example.api.config.OpenAIConfig;

    @Service
    public class ChatService {
        private final WebClient webClient;
        private final OpenAIConfig openAIConfig;

        public ChatService(OpenAIConfig openAIConfig){
        this.openAIConfig = openAIConfig;
        this.webClient = WebClient.builder()
                        .baseUrl("https://api.openai.com/v1/chat/completions")
                        .defaultHeader("Authorization","Bearer "+ openAIConfig.getOpenAiKey())
                        .build();
        }

        public String gerarResposta(String mensagemUsuario, String intencao, String respostaSql){
            String prompt = "Você é uma assistente que responde pergutas com base nas informações disponiveis.\n"
                        + "Pergunta"+ mensagemUsuario +"\n"
                        + "Contexto: Intenção: "+ intencao+". Reposta: "+respostaSql;

            Map<String,Object> RequestBody = Map.of(
                "model", "gpt-4.1-mini",
                "messages", List.of(
                    Map.of("role","system", "content", "Você é um assistente educado, direto ao ponto e brincalhão."),
                    Map.of("role", "user", "content", prompt)
                ),
                "temperature",0.7
            );
            Map<String,Object> response = webClient.post()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(RequestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

            List<?> choices = (List<?>) response.get("choices");
            Map<String,Object> firstChoice = (Map<String,Object>) choices.get(0);
            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
        String content = (String) message.get("content");
            
            return content;
        }

    }
