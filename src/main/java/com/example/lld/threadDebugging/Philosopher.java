package com.example.lld.threadDebugging;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;

    private final int MAX_RETRIES = 10;

    private ReentrantLock leftForkLock;
    private ReentrantLock rightForkLock;

    public Philosopher(Object left, Object right, ReentrantLock leftLock, ReentrantLock rightLock) {
        this.leftFork = left;
        this.rightFork = right;
        leftForkLock = leftLock;
        rightForkLock = rightLock;
    }


    public void run() {
        int x = 0;
        while (x<10) {
            try {
                for(int i=0; i<MAX_RETRIES; i++){
                    if(leftForkLock.tryLock(50, TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() + " picked up left fork in attempt " + i);
                        for(int j=0; j<MAX_RETRIES; j++){
                            if(rightForkLock.tryLock(50, TimeUnit.MILLISECONDS)){
                                System.out.println(Thread.currentThread().getName() + " picked up right fork and is eating in attempt " + j);
                                break;
                            }
                            else{
                                System.out.println(Thread.currentThread().getName() + " failed to pick right fork in attempt " + j);
                            }
                            Thread.sleep(ThreadLocalRandom.current().nextInt(1, 100));

                        }
                        break;
                    }
                    else {
                        System.out.println(Thread.currentThread().getName() + " failed to pick left fork in attempt " + i);
                    }
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1, 100));
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(leftForkLock.isHeldByCurrentThread()){
                    leftForkLock.unlock();
                }
                if(rightForkLock.isHeldByCurrentThread()){
                    rightForkLock.unlock();
                }
            }
            // Simulate thinking time
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            x++;
        }
    }
}