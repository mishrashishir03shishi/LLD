package com.example.lld.threadDebugging;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {

    private AtomicInteger count = new AtomicInteger(0);

    public void increment(){
        this.count.incrementAndGet();
    }

    public void decrement(){
        this.count.decrementAndGet();
    }

    public int getCount(){
        return this.count.get();
    }
}
