package com.example.lld.blockingqueue;

public class QueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueueImpl<Integer> queue = new ArrayBlockingQueueImpl<>(5);

        // Producer 1
        Thread producer1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
//                    System.out.println("Producer 1 putting: " + i);
                    queue.put(i);
//                    System.out.println("Producer 1 completed: " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Producer 2
        Thread producer2 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                try {
//                    System.out.println("Producer 2 putting: " + i);
                    queue.put(i);
//                    System.out.println("Producer 1 completed: " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer 1
        Thread consumer1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Integer val = queue.take();
//                    System.out.println("Consumer 1 took: " + val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer 2
        Thread consumer2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Integer val = queue.take();
//                    System.out.println("Consumer 2 took: " + val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
