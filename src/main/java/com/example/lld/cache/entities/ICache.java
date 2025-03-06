package com.example.lld.cache.entities;

public interface ICache<K, V> {

    void put(K key, V value);

    V get(K Key);
}
