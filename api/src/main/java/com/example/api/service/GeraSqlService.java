package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.config.OpenAIConfig;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

@Service
public class GeraSqlService {

        private final OpenAIClient client;

        public GeraSqlService(OpenAIConfig openAIConfig) {
                this.client = OpenAIOkHttpClient.builder()
                                .apiKey(openAIConfig.getOpenAiKey())
                                .build();
        }

        public String geraSql(String jsonBanco, String mensagemUsuario) {

                String prompt = """
                                Você está recebendo a estrutura do banco de dados em JSON e uma pergunta de um usuário leigo.

                                Banco (siga estritamente os nomes de tabelas e colunas abaixo, e nenhum outro):
                                {
                                  "fornecedores": {
                                    "id": "int",
                                    "nome": "varchar",
                                    "telefone": "varchar"
                                  }
                                }

                                Exemplo de como responder:
                                Pergunta do usuário: Qual é o celular do fornecedor X?
                                Resposta correta: SELECT telefone FROM fornecedores WHERE nome = 'X';

                                Note que mesmo que o usuário diga "celular", no banco a coluna é "telefone". Sempre use apenas os nomes do banco!

                                Agora, gere a SQL para o seguinte banco:
                                %s

                                Pergunta do usuário:
                                %s

                                IMPORTANTE:
                                - Use somente os nomes de tabelas e colunas do banco acima.
                                - NÃO use palavras da pergunta que não estejam no banco.
                                - Nunca retorne explicações. Apenas a query SQL.
                                """
                                .formatted(jsonBanco, mensagemUsuario);

                ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                                .model(ChatModel.GPT_4_1_MINI)
                                .addSystemMessage(
                                                """
                                                                Você é um assistente que gera exclusivamente SQL.
                                                                Atenção:
                                                                - Você NUNCA deve usar nomes de colunas ou tabelas que não estejam explicitamente descritos no banco fornecido.
                                                                - A pergunta do usuário serve apenas para você entender a intenção.
                                                                - SEMPRE utilize somente os nomes exatos das tabelas e colunas que estão no banco.
                                                                - Não invente colunas ou relacione nomes parecidos. Siga exatamente o que está no banco.
                                                                - A resposta deve conter SOMENTE a query SQL, sem explicações, nem mensagens adicionais.
                                                                - Começe pelo select, não use ´´´´ sql ´´´´.
                                                                
                                                                """)
                                .addUserMessage(prompt)
                                .temperature(0.0)
                                .build();

                ChatCompletion response = client.chat().completions().create(params);

                return response.choices().get(0).message().content().orElse("Erro ao gerar SQL");
        }
}
