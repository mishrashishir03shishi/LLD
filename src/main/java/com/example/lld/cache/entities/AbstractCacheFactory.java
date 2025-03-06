package com.example.lld.cache.entities;

public interface AbstractCacheFactory {

    <K, V> ICache<K, V> getNewCache(Integer size);

}
