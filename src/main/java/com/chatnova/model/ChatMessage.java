package com.chatnova.model;

public class ChatMessage extends BaseMessage {

    private int id;
    private String userMessage;
    private String botResponse;

    public ChatMessage() {
        super();
    }

    public ChatMessage(String userMessage, String botResponse) {
        super(userMessage);
        this.userMessage = userMessage;
        this.botResponse = botResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public void setBotResponse(String botResponse) {
        this.botResponse = botResponse;
    }
}