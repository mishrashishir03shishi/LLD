package com.example.lld.threadDebugging;

public interface BoundedBlockingQueue {

    void put(int a);

    int take();

    int size();
}
