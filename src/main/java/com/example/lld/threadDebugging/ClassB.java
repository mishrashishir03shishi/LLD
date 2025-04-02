package com.example.lld.threadDebugging;

public class ClassB {

    private final String myString;

    public ClassB(String myString) {
        this.myString = myString;
    }

     void methodB(ClassA a) {
        synchronized (myString){
            System.out.println("Thread 2: Holding B, waiting for A");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            a.last();
        }
    }

    void last() {
        System.out.println("Inside B.last()");
    }
}
