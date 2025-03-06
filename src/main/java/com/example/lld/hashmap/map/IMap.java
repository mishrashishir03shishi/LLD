package com.example.lld.hashmap.map;

public interface IMap <K, V>{

    void put(K key, V value);

    V get(K key);

    int size();

    void remove(K key);


}
