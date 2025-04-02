package com.example.lld.threadDebugging;

public class MyCountDownLatchImpl implements MyCountDownLatch{

    private int count;

    public MyCountDownLatchImpl(int count) {
        if(count<0){
            throw new RuntimeException("Invalid count");
        }
        this.count = count;
    }

    @Override
    public synchronized void await() {
        //while count is greater than 0, keep waiting
        while (this.count>0){
            try{
                wait();
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public synchronized void countDown() {
        // decrement count, if count ==0 notify all the waiting threads
        //make it thread safe -> either use synchronised/atomicinteger
        if(this.count==0) return;
        this.count--;
        notifyAll();

    }

    @Override
    public synchronized int getCount() {
        return this.count;
    }
}
