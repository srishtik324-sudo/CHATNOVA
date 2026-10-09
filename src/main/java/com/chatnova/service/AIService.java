
package com.chatnova.service;

import com.chatnova.model.ChatMessage;
import com.chatnova.repository.ChatMessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AIService implements ChatService {

    private final ChatMessageRepository repository;
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public AIService(ChatMessageRepository repository,
                     ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }

    @Override
    public String getResponse(String message) {
        try {
            String requestJson = objectMapper.createObjectNode()
                    .put("model", "llama3.2")
                    .put("prompt", message)
                    .put("stream", false)
                    .toString();

            String result = restClient.post()
                    .uri("/api/generate")
                    .header("Content-Type", "application/json")
                    .body(requestJson)
                    .retrieve()
                    .body(String.class);

            JsonNode json = objectMapper.readTree(result);
            String response = json.path("response").asText("");

            if (response.isBlank()) {
                throw new IllegalStateException(
                        "Ollama returned an empty response"
                );
            }

            repository.save(new ChatMessage(message, response));
            return response;

        } catch (Exception e) {
            System.err.println("CHATNOVA LOCAL AI ERROR:");
            e.printStackTrace();
            throw new RuntimeException(
                    "Local AI failed: " + e.getMessage(), e
            );
        }
    }
}