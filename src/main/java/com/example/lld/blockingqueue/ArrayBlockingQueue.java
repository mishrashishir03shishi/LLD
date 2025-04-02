package com.example.lld.blockingqueue;

public interface ArrayBlockingQueue<T> {

    void put(T object);

    T take();

    int size();

    boolean isEmpty();


}
