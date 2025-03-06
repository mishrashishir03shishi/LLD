package com.example.lld.cache;

import com.example.lld.cache.entities.LRUCache;
import com.example.lld.cache.entities.LRUCacheFactory;

public class CacheManagementPlatform {

    public static void run(){
        LRUCache lruCache = LRUCacheFactory.getInstance().getNewCache(2);
        lruCache.put("1", "1");
        lruCache.viewCache();
        lruCache.put("2", "2");
        lruCache.viewCache();
        lruCache.get("1");
        lruCache.viewCache();
        lruCache.put("3", "3");
        lruCache.viewCache();
        lruCache.get("2");
        lruCache.viewCache();
        lruCache.put("4", "4");
        lruCache.viewCache();
        lruCache.get("1");
        lruCache.viewCache();
        lruCache.get("3");
        lruCache.viewCache();
        lruCache.get("4");
        lruCache.viewCache();
    }
}
