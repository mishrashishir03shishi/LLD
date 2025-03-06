package com.example.lld.cache.entities;

public interface EvictionPolicy<K> {

    K evictKey();

    void updateKeyAccessed(K key);
}
