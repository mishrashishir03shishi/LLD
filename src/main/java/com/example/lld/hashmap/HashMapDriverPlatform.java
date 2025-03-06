package com.example.lld.hashmap;

import com.example.lld.hashmap.map.MyMap;
import com.example.lld.hashmap.strategies.HashingStrategy;
import com.example.lld.hashmap.strategies.ObjectHashingStrategy;

public class HashMapDriverPlatform {

    private static HashMapDriverPlatform instance;

    private HashMapDriverPlatform() {
    }

    public static HashMapDriverPlatform getInstance() {
        if(instance==null){
            instance = new HashMapDriverPlatform();
        }
        return instance;
    }

    public void run(){

        HashingStrategy<Integer> strategy = new ObjectHashingStrategy<>();

        MyMap<Integer, String> map = new MyMap<>(strategy);

        System.out.println("Adding elements to the map...");

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");

        System.out.println("Size after insertions: " + map.size());  // Expected: 4
        System.out.println("Get 2: " + map.get(2)); // Expected: "Two"
        System.out.println("Get 4: " + map.get(4)); // Expected: "Four"

        System.out.println("\nTesting collision handling...");

        // Insert keys that will intentionally cause collisions
        map.put(17, "Seventeen");  // Assuming initial size is 16, this will collide with 1
        map.put(33, "Thirty-Three"); // Assuming hash function, might collide with 1, 17

        System.out.println("Get 17: " + map.get(17)); // Expected: "Seventeen"
        System.out.println("Get 33: " + map.get(33)); // Expected: "Thirty-Three"

        System.out.println("\nTesting resize operation...");

        for (int i = 5; i <= 20; i++) {
            map.put(i, "Value" + i);
        }

        System.out.println("Size after resizing: " + map.size()); // Expected: 20 (or more)
        System.out.println("Get 20: " + map.get(20)); // Expected: "Value20"
        System.out.println("Get 17: " + map.get(17)); // Expected: "Seventeen"

        System.out.println("\nTesting removal...");

        map.remove(2);
        map.remove(4);
        map.remove(17);

        System.out.println("Size after removals: " + map.size()); // Expected: Less than before
        System.out.println("Get 2: " + map.get(2)); // Expected: null
        System.out.println("Get 17: " + map.get(17)); // Expected: null

        System.out.println("\nFinal verification...");

        for (int i = 1; i <= 20; i++) {
            if (map.get(i) != null) {
                System.out.println("Key " + i + " -> " + map.get(i));
            }
        }

        System.out.println("\nAll tests completed successfully.");


    }
}
