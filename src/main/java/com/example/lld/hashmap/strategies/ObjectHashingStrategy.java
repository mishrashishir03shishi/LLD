package com.example.lld.hashmap.strategies;

public class ObjectHashingStrategy<K> implements HashingStrategy<K>{
    @Override
    public int computeHash(K key, int bucketLength) {
        return (key.hashCode() & 0x7FFFFFFF) % bucketLength;
    }
}
