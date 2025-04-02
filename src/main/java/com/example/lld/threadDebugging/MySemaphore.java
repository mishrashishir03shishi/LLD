package com.example.lld.threadDebugging;

public interface MySemaphore {

    void acquire();

    void release();

    int getAvailablePermits();

}
