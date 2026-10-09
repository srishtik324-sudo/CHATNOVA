package com.chatnova.model;

public class BaseMessage {

    private String content;

    public BaseMessage() {
    }

    public BaseMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}