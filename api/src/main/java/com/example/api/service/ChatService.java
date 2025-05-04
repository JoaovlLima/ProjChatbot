    package com.example.api.service;

    import java.util.List;
    import java.util.Map;

    import org.springframework.stereotype.Service;

    import com.example.api.config.OpenAIConfig;
    import com.openai.client.OpenAIClient;
    import com.openai.client.okhttp.OpenAIOkHttpClient;
    import com.openai.models.ChatModel;
    import com.openai.models.chat.completions.ChatCompletion;
    import com.openai.models.chat.completions.ChatCompletionCreateParams;

    @Service
    public class ChatService {
        private final OpenAIClient client;

        public ChatService(OpenAIConfig openAIConfig){
            this.client = OpenAIOkHttpClient.builder()
                     .apiKey(openAIConfig.getOpenAiKey())
                     .build();

        }

        public String gerarResposta(String mensagemUsuario, String intencao, String respostaSql){
            String prompt = "Você é uma assistente que responde pergutas com base nas informações disponiveis.\n"
                        + "Pergunta"+ mensagemUsuario +"\n"
                        + "Contexto: Intenção: "+ intencao+". Reposta: "+respostaSql;
            ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                        .model(ChatModel.GPT_4_1_MINI)
                        .addSystemMessage("Você é um assistente educado, direto ao ponto e brincalhão. Foque apenas na resposta sem dar sugestões.")
                        .addUserMessage(prompt)
                        .temperature(0.7)
                        .build();

            ChatCompletion response = client.chat().completions().create(params);
            
             return response.choices().get(0).message().content().orElse("Sem Resposta");
            
        }

        

    }
