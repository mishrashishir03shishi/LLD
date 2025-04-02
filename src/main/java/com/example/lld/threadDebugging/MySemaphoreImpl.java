package com.example.lld.threadDebugging;

public class MySemaphoreImpl implements MySemaphore{

    private final int permits;

    private int counter;

    public MySemaphoreImpl(int permits) {
        if(permits<0){
            throw new IllegalArgumentException("Invalid permits");
        }
        this.permits = permits;
        this.counter = permits;
    }

    /*
        1. blocks access until counter is 0.
        2. Decrement the counter when allowed to proceed.
        3. Be thread safe.
         */
    @Override
    public synchronized void acquire() {
        while(this.counter==0){
            try{
                wait();
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        this.counter--;
    }

    /*
    1. Increments the counter (Does nothing when the counter is == permits)
    2. Notifies all the waiting threads
     */
    @Override
    public synchronized void release() {
        if(this.counter==permits) return;
        this.counter++;
        notifyAll();
    }

    @Override
    public synchronized int getAvailablePermits() {
        return this.counter;
    }
}
