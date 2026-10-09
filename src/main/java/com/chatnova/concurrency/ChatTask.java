
package com.chatnova.concurrency;

public class ChatTask implements Runnable {

    private final String message;

    public ChatTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(
            "Processing chat message on thread: "
            + Thread.currentThread().getName()
            + " | Message: " + message
        );
    }
}
