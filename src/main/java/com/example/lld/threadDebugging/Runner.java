package com.example.lld.threadDebugging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private static volatile boolean flag = false;

//    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final Object lock = new Object();

    static int counter = 0;

    private static volatile boolean stop = false;

    private static volatile int signal = 0;

    private static volatile boolean ready = false;

//    static final Object resourceA = new Object();
//    static final Object resourceB = new Object();

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    private static volatile boolean aDone = false;
    private static volatile boolean bDone = false;

    public static void main(String[] args) throws InterruptedException {

//        Race Condition
//        Counter counter = new Counter();
//        Thread t1 = new Thread(() -> {
//            for(int i=0; i<10000; i++){
//                counter.increment();
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            for(int i=0; i<10000; i++){
//                counter.increment();
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.out.println("Counter : " + counter.getCounter());

//        DeadLock
//        String hello = "hello";
//        ClassA a = new ClassA(hello);
//        ClassB b = new ClassB(hello);
//
//        Thread t1 = new Thread(() -> {
//            a.methodA(b);
//        });
//
//        Thread t2 = new Thread(() -> {
//            b.methodB(a);
//        });
//
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

//        Visibility Problem
//        Thread writer = new Thread(() -> {
//            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
//            flag = true;
//            System.out.println("Flag set to true");
//        });
//
//        Thread reader = new Thread(() -> {
//            while (!flag) {
//                // busy wait
//            }
//            System.out.println("Reader thread saw flag = true");
//        });
//
//        writer.start();
//        reader.start();
//
//        writer.join();
//        reader.join();

//        Starvation and Improper Lock Usage
//        Runnable longRunningTask = () -> {
//            while (true) {
//                lock.lock();
//                try {
//                    System.out.println(Thread.currentThread().getName() + " got lock");
//                    Thread.sleep(3000); // Simulate long task
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                } finally {
//                    lock.unlock();
//                }
//                try {
//                    Thread.sleep(10); // Small delay before trying again
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        };
//
//        Runnable quickTask = () -> {
//            for (int i = 0; i < 5; i++) {
//                lock.lock();
//                try {
//                    System.out.println(Thread.currentThread().getName() + " got lock for quick task " + i);
//                } finally {
//                    lock.unlock();
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        };
//
//        Thread longWorker1 = new Thread(longRunningTask, "LongWorker-1");
//        Thread longWorker2 = new Thread(longRunningTask, "LongWorker-2");
//        Thread quickWorker = new Thread(quickTask, "QuickWorker");
//
//        longWorker1.start();
//        longWorker2.start();
//        quickWorker.start();
//
//        longWorker1.join();
//        longWorker2.join();
//        quickWorker.join();

//        Join Condition
//        Thread t = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                counter++;
//            }
//        });
//
//        t.start();
//        t.join(); // Waits for thread to finish
//        System.out.println("Final counter: " + counter); // Expect 1000

//        Object fork1 = new Object();
//        Object fork2 = new Object();
//        Object fork3 = new Object();
//        Object fork4 = new Object();
//        Object fork5 = new Object();
//
//        ReentrantLock lock1 = new ReentrantLock();
//        ReentrantLock lock2 = new ReentrantLock();
//        ReentrantLock lock3 = new ReentrantLock();
//        ReentrantLock lock4 = new ReentrantLock();
//        ReentrantLock lock5 = new ReentrantLock();
//
//        Thread p1 = new Thread(new Philosopher(fork1, fork2, lock1, lock2), "P1");
//        Thread p2 = new Thread(new Philosopher(fork2, fork3, lock2, lock3), "P2");
//        Thread p3 = new Thread(new Philosopher(fork3, fork4, lock3, lock4), "P3");
//        Thread p4 = new Thread(new Philosopher(fork4, fork5, lock4, lock5), "P4");
//        Thread p5 = new Thread(new Philosopher(fork5, fork1, lock5, lock1), "P5");
//
//        p1.start(); p2.start(); p3.start(); p4.start(); p5.start();

//        SharedCounter sharedCounter = new SharedCounter();
//
//        Thread t1 = new Thread(() -> {
//            for(int i=0; i<100000; i++){
//                sharedCounter.increment();
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            for(int i=0; i<100000; i++){
//                sharedCounter.decrement();
//            }
//        });
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//
//        System.out.println(sharedCounter.getCount());

//        Thread worker = new Thread(() -> {
//            int count = 0;
//            while (!stop) {
//                count++;
//            }
//            System.out.println("Loop exited. Count = " + count);
//        });
//
//        worker.start();
//
//        Thread.sleep(10000);
//        stop = true;
//        System.out.println("Main thread set stop = true");
//
//        worker.join();
//        System.out.println("Main thread exiting");

//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        Thread worker = new Thread(() -> {
//            synchronized (lock) {
//                System.out.println("Worker acquired lock and is working...");
//                try {
//                    Thread.sleep(500);
//                    countDownLatch.await();
//                    System.out.println("Worker resumed after wait");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        worker.start();
//
//        synchronized (lock) {
//            System.out.println("Main thread notifying worker");
//            countDownLatch.countDown();
//        }
//
//        worker.join();
//        System.out.println("Main thread exiting");


//        Thread worker = new Thread(() -> {
//            synchronized (lock) {
//                System.out.println("Worker acquired lock and is working...");
//                try {
//                    Thread.sleep(500);
//                    while(signal==0){
//                        lock.wait();
//                    }
//                    System.out.println("Worker resumed after wait");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        worker.start();
//
//        Thread.sleep(10000);
//
//        synchronized (lock) {
//            System.out.println("Main thread notifying worker");
//            signal++;
//            lock.notify();
//        }
//
//        worker.join();
//        System.out.println("Main thread exiting");

//        Thread t1 = new Thread(() -> {
//            synchronized (lock) {
//                while (!ready) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("Thread 1: Proceeding after being notified");
//            }
//        });
//
//        t1.start();
//
//        // Main thread sleeps before acquiring the lock and notifying
//        Thread.sleep(2000);
//
//        synchronized (lock) {
//            ready = true;
//            System.out.println("Main thread: Notifying");
//            lock.notify();
//        }
//
//        t1.join();

//        Thread t1 = new Thread(() -> {
//            synchronized (resourceA) {
//                System.out.println("Thread-1: Holding resourceA");
//                try {
//                    Thread.sleep(100); // ensure t2 locks B first
//                } catch (InterruptedException ignored) {}
//
//
//                try {
//                    System.out.println("Thread-1: Waiting on A");
//
//                    while(!ready){
//                        resourceA.wait();
//                    }
//                    System.out.println("Thread-1: Resumed");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            synchronized (resourceB) {
//                System.out.println("Thread-2: Holding resourceB");
//                try {
//                    Thread.sleep(100); // ensure t1 locks A first
//                } catch (InterruptedException ignored) {}
//
//                synchronized (resourceA) {
//                    System.out.println("Thread-2: Holding resourceA");
//                    ready = true;
//                    resourceA.notify(); // trying to wake t1
//                    System.out.println("Thread-2: Notified on A");
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.out.println("Main thread exiting");
//    }

        //CountDown Latch
//        CountDownLatch latchA = new CountDownLatch(1);
//
//        CountDownLatch latchB = new CountDownLatch(1);
//
//        Thread threadB = new Thread(() -> {
//
//                try {
//                    latchB.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Thread B: Proceeding after A");
//        });
//
//        Thread threadA = new Thread(() -> {
//                try {
//                    latchA.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                latchB.countDown();
//                System.out.println("Thread A: Proceeding after C");
//        });
//
//        Thread threadC = new Thread(() -> {
//            try {
//                Thread.sleep(200); // give time for A and B to start
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            latchA.countDown();
//            System.out.println("Thread C: Notified A");
//
//        });
//
//        /*
//        1. threadA acquires lockA
//        2. threadB acquires lockB
//        3. threadA waits and leaves lockA
//        4. threadB waits and leaves lockB
//        5. threadC acquires lockA
//        6. bDone = true, lockA.notify()
//        7. threadA wakes, exits while loop
//        8. threadA acquires lockB, adone=true, lockB.notify()
//        9. threadB wakes, bdone = true;
//        10. program ends
//
//         */
//
//        threadB.start();
//        threadA.start();
//        threadC.start();
//
//        threadB.join();
//        threadA.join();
//        threadC.join();
//
//        System.out.println("Main thread exiting.");

        ConcurrentWriter concurrentWriter = new ConcurrentWriter(3);
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<10; i++){
            int finalI = i;
            Thread t = new Thread(() -> {concurrentWriter.write("Data " + finalI);}, "Thread " + i);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads){
            t.join();
        }
        System.out.println("Main thread exiting.");


    }

}
