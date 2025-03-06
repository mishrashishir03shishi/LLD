package com.example.lld.cache.entities;

public class LRUCache extends BaseCache<String, String> {


    protected LRUCache(Integer size) {
        super(new LRUEvictionPolicy(), size);
    }

    @Override
    public void put(String key, String value) {
        super.put(key, value);
    }

    @Override
    public String get(String Key) {
        return super.get(Key);
    }
}
