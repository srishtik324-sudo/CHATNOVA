
package com.chatnova.concurrency;

public class SynchronizationTest {

    public static void main(String[] args) throws InterruptedException {

        ChatCounter counter = new ChatCounter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Expected count: 20000");
        System.out.println("Actual count: " + counter.getCount());
    }
}
