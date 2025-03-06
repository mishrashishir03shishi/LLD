package com.example.lld.cache.entities;

public class LRUCacheFactory implements AbstractCacheFactory{

    private static LRUCacheFactory lruCacheFactory;

    private LRUCacheFactory() {
    }

    public static LRUCacheFactory getInstance(){
        if(lruCacheFactory==null){
            return new LRUCacheFactory();
        }
        return lruCacheFactory;
    }
    @Override
    public LRUCache getNewCache(Integer size) {
        return new LRUCache(size);
    }

}
