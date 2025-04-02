package com.example.lld.threadDebugging;

public class Counter {

    private int counter = 0;

    public synchronized void increment(){
        counter++;
    }

    public int getCounter(){
        return this.counter;
    }
}
