package com.email.writer.client;

import com.email.writer.dto.request.EmailGeneratorRequest;
import com.email.writer.dto.response.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GeminiClient {
    private final WebClient.Builder webClientBuilder;

    @Value("${gemini.api.url}")
    private  String baseUrl;
    @Value("${gemini.api.key}")
    private  String apiKey;

    public String buildPrompt(EmailGeneratorRequest emailGeneratorRequest) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Create a professional reply for the following email:\n");
        if(emailGeneratorRequest.getTone() != null && !emailGeneratorRequest.getTone().isBlank()) {
            promptBuilder.append("Tone: " ).append(emailGeneratorRequest.getTone()).append("\n");
        }
        promptBuilder.append("Email: \n").append(emailGeneratorRequest.getEmailContent());
        return promptBuilder.toString();
    }

    public String buildGeminiRequest(String prompt) {
        return String.format("""
                {
                    "contents": [
                      {
                        "parts": [
                          {
                            "text": "%s"
                          }
                        ]
                      }
                    ]
                  }
                """, prompt);
    }

    public GeminiResponse callGeminiApi(String body) {
        WebClient webClient = webClientBuilder.build();
        return webClient.post()
                .uri(baseUrl)
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();
    }
}
