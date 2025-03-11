package com.example.lld.hashmap.map;

import com.example.lld.hashmap.strategies.HashingStrategy;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyConcurrentHashMap<K, V> implements IMap<K, V>{
    private static final int INITIAL_CAPACITY = 16;

    private static final double LOAD_FACTOR = 0.75;

    private volatile Node<K,V>[] nodeList = new Node[INITIAL_CAPACITY];

    private ReentrantReadWriteLock[] locks = new ReentrantReadWriteLock[INITIAL_CAPACITY];

    private ReentrantLock resizingLock = new ReentrantLock();

    private AtomicBoolean isResizing = new AtomicBoolean(false);

    private AtomicInteger size = new AtomicInteger(0);

    private final HashingStrategy<K> strategy;

    public MyConcurrentHashMap(HashingStrategy<K> strategy) {
        this.strategy = strategy;
        for(int i=0; i<INITIAL_CAPACITY; i++){
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    @Override
    public void put(K key, V value) {
        int index = strategy.computeHash(key, nodeList.length);
        System.out.println(Thread.currentThread().getName() + " entered put operation : index " + index);
        while(isResizing.get()){
            try{
                System.out.println(Thread.currentThread().getName() + " resizing going on in put : index " + index);
                Thread.sleep(1);
            }
            catch (Exception e){
                System.out.println(Thread.currentThread().getName() + " error while sleeping ");
            }
        }
        locks[index].writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " acquired write lock for put : " + index);
        try{
            if(nodeList[index]==null){
                Node<K, V> node = new Node<>(key, value);
                nodeList[index] = node;
            }
            else {
                Node<K, V> startingNode = nodeList[index];
                Node<K, V> prevNode = startingNode;
                while(startingNode!=null){
                    if(startingNode.key==key){
                        startingNode.value = value;
                        return;
                    }
                    prevNode = startingNode;
                    startingNode = startingNode.next;
                }
                prevNode.next = new Node<>(key, value);
            }
            this.size.incrementAndGet();
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " releasing write lock for put : index " + index);
            locks[index].writeLock().unlock();
        }
        if (size.get() >= (int) (nodeList.length * LOAD_FACTOR)) {
            System.out.println(Thread.currentThread().getName() + " resizing required ");
            resizingLock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired resizing lock ");
            try {
                if (size.get() >= (int) (nodeList.length * LOAD_FACTOR) && isResizing.compareAndSet(false, true)) {
                    resize();
                    isResizing.set(false);
                }
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing resizing lock ");
                resizingLock.unlock();
            }
        }
    }

    @Override
    public V get(K key) {
        int index = strategy.computeHash(key, nodeList.length);
        System.out.println(Thread.currentThread().getName() + " entered get operation : index " + index);
        locks[index].readLock().lock();
        System.out.println(Thread.currentThread().getName() + " acquired reading lock : index " + index );
        Node<K, V> node = nodeList[index];
        while(node!=null){
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }
        System.out.println(Thread.currentThread().getName() + " releasing reading lock : index " + index);
        locks[index].readLock().unlock();
        return null;
    }

    @Override
    public int size() {
        return this.size.get();
    }

    @Override
    public void remove(K key) {
        int index = strategy.computeHash(key, nodeList.length);
        System.out.println(Thread.currentThread().getName() + " entered remove operation : index " + index);
        while(isResizing.get()){
            try{
                System.out.println(Thread.currentThread().getName() + " resizing going on in put : index " + index);
                Thread.sleep(1);
            }
            catch (Exception e){
                System.out.println(Thread.currentThread().getName() + " error while sleeping ");
            }
        }
        locks[index].writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " acquired write lock for remove : index " + index);
        Node<K, V> node = nodeList[index];
        Node<K, V> prev = null;
        while(node!=null){
            if(node.key.equals(key)){
                break;
            }
            prev = node;
            node = node.next;
        }
        if(node!=null){
            if(prev!=null){
                prev.next = node.next;
                node.next = null;
            }
            else {
                nodeList[index] = node.next;
                node.next = null;
            }
            size.decrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + " releasing write lock for remove : index " + index);
        locks[index].writeLock().unlock();

    }

    private void resize(){
        if (size.get() < (int) (nodeList.length * LOAD_FACTOR)) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + " started resizing operation ");
        int newSize = nodeList.length*2;
        Node<K,V>[] tempNodeList = new Node[newSize];
        ReentrantReadWriteLock[] newLocks = new ReentrantReadWriteLock[newSize];

        for(int i=0; i<newSize; i++){
            newLocks[i] = new ReentrantReadWriteLock();
        }
        //recompute the hashes and store
        for (int i=0; i<nodeList.length; i++) {
            Node<K, V> tempNode = nodeList[i];
            while (tempNode != null) {
                int index = strategy.computeHash(tempNode.key, newSize);
                if (tempNodeList[index] != null) {
                    //insert at the head as there cant be any duplicates
                    Node<K, V> newNode = new Node<>(tempNode.key, tempNode.value);
                    newNode.next = tempNodeList[index];
                    tempNodeList[index] = newNode;
                } else {
                    tempNodeList[index] = new Node<>(tempNode.key, tempNode.value);
                }
                tempNode = tempNode.next;
            }
        }
        nodeList = tempNodeList;
        locks = newLocks;
        System.out.println(Thread.currentThread().getName() + " completed resizing ");
    }

    public void print(){
        for (int i=0; i<nodeList.length; i++){
            System.out.println("index -> " + i);
            Node<K, V> tempNode = nodeList[i];
            while (tempNode!=null){
                System.out.print("Key : " + tempNode.key + " Value : " + tempNode.value + ", ");
                tempNode = tempNode.next;
            }
            System.out.println();
        }
    }
}
