
package com.chatnova.concurrency;

public class ConcurrencyTest {

    public static void main(String[] args) {

        ChatTask task1 = new ChatTask("Hello from ChatNova");
        ChatTask task2 = new ChatTask("Testing multithreading");

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
