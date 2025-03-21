package com.example.lld.hashmap.map;

public class Node<K, V> {

    K key;
    V value;

    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
