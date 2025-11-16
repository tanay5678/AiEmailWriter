package com.email.writer.controllers;

import com.email.writer.dto.request.EmailGeneratorRequest;
import com.email.writer.services.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailGeneratorRequest emailGeneratorRequest) {
        String response = emailGeneratorService.generateEmailReply(emailGeneratorRequest);
        return ResponseEntity.ok(response);
    }
}
