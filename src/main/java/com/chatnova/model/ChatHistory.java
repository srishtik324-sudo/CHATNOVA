package com.chatnova.model;

import java.util.ArrayList;
import java.util.List;

public class ChatHistory<T> {

    private final List<T> messages = new ArrayList<>();

    public void addMessage(T message) {
        messages.add(message);
    }

    public List<T> getMessages() {
        return new ArrayList<>(messages);
    }

    public int getCount() {
        return messages.size();
    }
}