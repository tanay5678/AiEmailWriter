package com.email.writer.services;

import com.email.writer.client.GeminiClient;
import com.email.writer.dto.request.EmailGeneratorRequest;
import com.email.writer.dto.response.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailGeneratorService {
    private final GeminiClient geminiClient;

    public String generateEmailReply(EmailGeneratorRequest emailGeneratorRequest) {
        // build prompt
        String prompt = geminiClient.buildPrompt(emailGeneratorRequest);
        // prepare new JSON body
        String body = geminiClient.buildGeminiRequest(prompt);
        // send request
        GeminiResponse response = geminiClient.callGeminiApi(body);

        return response.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }

}
