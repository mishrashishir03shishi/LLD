package com.example.lld.hashmap.strategies;

public interface HashingStrategy <K>{

    int computeHash(K key, int bucketLength);
}
