package com.example.lld.threadDebugging;

public class BoundedBlockingQueueImpl implements BoundedBlockingQueue{

    int[] arr;

    int head;

    int tail;

    int size;

    MySemaphore filledSlots;

    MySemaphore emptySlots;

    MySemaphore mutex;

    public BoundedBlockingQueueImpl(int capacity) {
        this.arr = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.filledSlots = new MySemaphoreImpl(0);
        this.emptySlots = new MySemaphoreImpl(capacity);
        this.mutex = new MySemaphoreImpl(1);
    }

    /*
        if the capacity is full wait
        else put the object at tail and increment it
        notify all waiting workers
         */
    @Override
    public void put(int a) {
        emptySlots.acquire();
        mutex.acquire();
        this.arr[this.tail] = a;
        this.tail = (this.tail+1)%arr.length;
        this.size++;
        mutex.release();
        filledSlots.release();
    }

    /*
    if the capacity is zero, wait
    else take the object from head of the queue and increment head
    notify all waiting producers
     */
    @Override
    public int take() {
        filledSlots.acquire();
        mutex.acquire();
        int res = this.arr[this.head];
        this.head = (this.head+1)%arr.length;
        this.size--;
        mutex.release();
        emptySlots.release();
        return res;
    }

    @Override
    public int size() {
        //acquire both semaphores then report size
        int length = 0;
        try{
            mutex.acquire();
            length = this.size;
        }
        finally {
            mutex.release();
        }
        return length;
    }
}
