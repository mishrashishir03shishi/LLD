package com.example.lld.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueueLockImpl<T> implements ArrayBlockingQueue<T>{

    private T[] arr;

    private static final int CAPACITY = 10;

    int size;

    int headIndex;

    int tailIndex;

    private final ReentrantLock lock;

    private final Condition notFull;

    private final Condition notEmpty;

    @SuppressWarnings("unchecked")
    public ArrayBlockingQueueLockImpl(int initialCapacity) {
        this();
        this.arr = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayBlockingQueueLockImpl() {
        this.arr = (T[]) new Object[CAPACITY];
        this.size = 0;
        this.headIndex = 0;
        this.tailIndex = 0;
        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    @Override
    public void put(T object) {
        lock.lock();
        try{
            while (this.size==this.arr.length){
                notFull.await();
            }
            this.arr[this.tailIndex] = object;
            this.tailIndex = (this.tailIndex + 1)%this.arr.length;
            this.size++;
            System.out.println(Thread.currentThread().getName() + " PUT: " + object + ", size = " + this.size);
            this.notEmpty.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public T take() {
        lock.lock();
        T res = null;
        try{
            while (this.size==0){
                notEmpty.await();
            }
            res = arr[this.headIndex];
            this.headIndex = (this.headIndex + 1)%this.arr.length;
            this.size--;
            System.out.println(Thread.currentThread().getName() + " TOOK: " + res + ", size = " + this.size);
            this.notFull.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            lock.unlock();
        }
        return res;
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return this.size==0;
        } finally {
            lock.unlock();
        }
    }
}
