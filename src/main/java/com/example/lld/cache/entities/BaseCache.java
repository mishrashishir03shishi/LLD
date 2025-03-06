package com.example.lld.cache.entities;

import java.util.HashMap;

public abstract class BaseCache<K, V> implements ICache<K, V> {

    private final HashMap<K, V> map = new HashMap<>();

    private final EvictionPolicy<K> evictionPolicy;

    private final Integer size;

    public BaseCache(EvictionPolicy<K> evictionPolicy, Integer size) {
        this.evictionPolicy = evictionPolicy;
        this.size = size;
    }

    @Override
    public void put(K key, V value) {
        if(map.size()==size && !map.containsKey(key)){
            K evictedKey = evictionPolicy.evictKey();
            map.remove(evictedKey);
        }
        map.put(key, value);
        evictionPolicy.updateKeyAccessed(key);
    }

    @Override
    public V get(K Key) {
        V value = null;
        if(map.containsKey(Key)){
            evictionPolicy.updateKeyAccessed(Key);
            value = map.get(Key);
        }
        System.out.println("Get " + Key  + " : " + value);
        return value;
    }

    public void viewCache(){
        System.out.println(map.toString());
    }
}
