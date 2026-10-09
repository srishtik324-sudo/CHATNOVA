
package com.chatnova.controller;

import com.chatnova.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private static final Logger logger =
            LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestBody String message) {

        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Please enter a message.");
        }

        try {
            String reply = chatService.getResponse(message);
            return ResponseEntity.ok(reply);

       } catch (Exception e) {
    logger.error("Chat request failed", e);

    return ResponseEntity.internalServerError()
            .body("Error: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }
}

