package com.example.lld.threadDebugging;

public interface MyCountDownLatch {

    void await();

    void countDown();

    int getCount();
}
