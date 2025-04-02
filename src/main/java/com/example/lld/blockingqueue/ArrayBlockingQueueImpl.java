package com.example.lld.blockingqueue;

public class ArrayBlockingQueueImpl<T> implements ArrayBlockingQueue<T> {

    private T[] arr;

    private static final int CAPACITY = 10;

    int size;

    int headIndex;

    int tailIndex;

    @SuppressWarnings("unchecked")
    public ArrayBlockingQueueImpl(int initialCapacity) {
        this();
        this.arr = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayBlockingQueueImpl() {
        this.arr = (T[]) new Object[CAPACITY];
        this.size = 0;
        this.headIndex = 0;
        this.tailIndex = 0;
    }

    /*
    This will be a synchronised/locked method where threads will put element on the tail index and tail head will be incremented
     */
    @Override
    public synchronized void put(T object) {
        //put the element at head index and then update it
        //first check whether head = tail -> If yes then make the threads wait
        while(this.size==this.arr.length){
            try{
                wait();
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        this.arr[this.tailIndex] = object;
        this.tailIndex = (this.tailIndex + 1)%this.arr.length;
        this.size++;
        System.out.println(Thread.currentThread().getName() + " PUT: " + object + ", size = " + this.size);
        notifyAll();

    }

    /*
    This will be a synchronised/locked method where threads will take the element from the head index and head will be decremented
     */
    @Override
    public synchronized T take() {
        while(this.size==0){
            try{
                wait();
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        T res = arr[this.headIndex];
        this.headIndex = (this.headIndex + 1)%this.arr.length;
        this.size--;
        System.out.println(Thread.currentThread().getName() + " TOOK: " + res + ", size = " + this.size);
        notifyAll();
        return res;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

}
