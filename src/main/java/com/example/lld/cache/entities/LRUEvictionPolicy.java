package com.example.lld.cache.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LRUEvictionPolicy implements EvictionPolicy<String>{

    private final TreeMap<Integer, String> counterMap = new TreeMap<>();

    private final HashMap<String, Integer> lastCounterForKeyMap = new HashMap<>();

    private Integer count = 0;

    @Override
    public String evictKey() {
        Map.Entry<Integer, String> entry = counterMap.pollFirstEntry();
        counterMap.remove(entry.getKey());
        lastCounterForKeyMap.remove(entry.getValue());
        return entry.getValue();

    }

    @Override
    public void updateKeyAccessed(String key) {
        counterMap.put(count, key);
        if(lastCounterForKeyMap.containsKey(key)){
            counterMap.remove(lastCounterForKeyMap.get(key));
        }
        lastCounterForKeyMap.put(key, count);
        count++;
    }
}
