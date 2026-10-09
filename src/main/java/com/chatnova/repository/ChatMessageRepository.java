package com.chatnova.repository;

import com.chatnova.model.ChatMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public ChatMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(ChatMessage message) {

        String sql = "INSERT INTO chat_messages (user_message, bot_response) VALUES (?, ?)";

        return jdbcTemplate.update(
                sql,
                message.getUserMessage(),
                message.getBotResponse()
        );
    }
}