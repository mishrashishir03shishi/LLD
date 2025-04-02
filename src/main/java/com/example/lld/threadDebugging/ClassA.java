package com.example.lld.threadDebugging;

public class ClassA {

    private final String myString;

    public ClassA(String myString) {
        this.myString = myString;
    }

     void methodA(ClassB b) {
        synchronized (myString){
            System.out.println("Thread 1: Holding A, waiting for B");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            b.last();
        }
    }

     void last() {
        System.out.println("Inside A.last()");
    }
}
