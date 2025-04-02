package com.example.lld.producerconsumer.counter;

public class Counter {

    private int counter = 0;

    public synchronized void increment(){
        this.counter++;
    }

    public int getCounter(){
        return this.counter;
    }
}
