package com.example.lld.threadDebugging;

import java.util.concurrent.locks.ReentrantLock;

public class FrameExchange {

    private Object frame = null;

    private boolean isFrameReady = false;
    private int framesProduced = 0;

    private int framesDrawn = 0;

    public synchronized void produce(Object newFrame){
        try{
            Thread.sleep(50); //simulate some work before producing
            this.frame = newFrame;
            framesProduced++;
            isFrameReady = true;
            notify();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void draw(){
        try{
            while (!isFrameReady){
                wait();
            }
            Thread.sleep(200); //draw the frame
            framesDrawn++;
            isFrameReady = false;
            this.frame = null;
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
