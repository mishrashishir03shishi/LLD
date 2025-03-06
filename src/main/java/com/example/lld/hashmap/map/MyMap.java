package com.example.lld.hashmap.map;

import com.example.lld.hashmap.strategies.HashingStrategy;

public class MyMap<K, V> implements IMap<K, V>{

    private static final int INITIAL_CAPACITY = 16;

    private static final double LOAD_FACTOR = 0.75;

    Node<K,V>[] nodeList = new Node[INITIAL_CAPACITY];

    private int size = 0;

    private final HashingStrategy<K> strategy;

    public MyMap(HashingStrategy<K> strategy) {
        this.strategy = strategy;
    }

    /*
    compute the index
    if node is not null, traverse the linked list and add to its end if does not exist ow update the value
    if it is null, add the value and increment size
    resize the array
     */
    @Override
    public void put(K key, V value) {
        int index = strategy.computeHash(key, nodeList.length);
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
        this.size++;
        resize();
    }

    @Override
    public V get(K key) {
        int index = strategy.computeHash(key, nodeList.length);
        Node<K, V> node = nodeList[index];
        while(node!=null){
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void remove(K key) {
        int index = strategy.computeHash(key, nodeList.length);
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
            size--;
        }

    }

    private void resize(){
        if(size >= (int)(nodeList.length*LOAD_FACTOR)){
            int newSize = nodeList.length*2;
            Node<K,V>[] tempNodeList = new Node[newSize];
            //recompute the hashes and store
            for (Node<K, V> kvNode : nodeList) {
                Node<K, V> tempNode = kvNode;
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
        }
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
