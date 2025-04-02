package com.example.lld.threadDebugging;

public class ConcurrentWriter {

    private final MySemaphore mySemaphore;

    public ConcurrentWriter(int permits) {
        this.mySemaphore = new MySemaphoreImpl(permits);
    }

    public void write(String s){
        try{
            mySemaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " starting to write data : " + s);
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " ended writing data");
            System.out.println(Thread.currentThread().getName() + " available permits : " + mySemaphore.getAvailablePermits());
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            mySemaphore.release();
        }
    }
}
