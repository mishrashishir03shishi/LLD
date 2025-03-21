package com.example.lld.hashmap;

import com.example.lld.hashmap.map.MyConcurrentHashMap;
import com.example.lld.hashmap.map.MyMap;
import com.example.lld.hashmap.strategies.HashingStrategy;
import com.example.lld.hashmap.strategies.ObjectHashingStrategy;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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

        System.out.println("All tests completed successfully.");


    }


    public void runConcurrentHashMap() throws InterruptedException {
        final int NUM_THREADS = 2; // Number of concurrent threads
        final int NUM_OPERATIONS = 10; // Operations per thread
        final MyConcurrentHashMap<Integer, String> myMap = new MyConcurrentHashMap<>(new ObjectHashingStrategy<>());

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        AtomicInteger successfulGets = new AtomicInteger(0);
        AtomicInteger successfulRemovals = new AtomicInteger(0);

        System.out.println("🚀 Running small multi-threaded test...");

        // Start multiple threads performing concurrent PUT, GET, REMOVE
        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.execute(() -> {
                try {
                    for (int j = 0; j < NUM_OPERATIONS; j++) {
                        Random r = new Random();
                        int key = r.nextInt(10);
                        String value = "Value" + key;
                        if (j % 3 == 0) { // Put operation (33%)
                            myMap.put(key, value);
                            System.out.println(Thread.currentThread().getName() + "-iteration-" + j + " put " + key);
                        } else if (j % 3 == 1) { // Get operation (33%)
                            String result = myMap.get(key);
                            if (result != null) {
                                successfulGets.incrementAndGet();
                            }
                            System.out.println(Thread.currentThread().getName() + "-iteration-" + j + " got " + key + " -> " + result);
                        } else { // Remove operation (33%)

                            myMap.remove(key);

                            System.out.println(Thread.currentThread().getName() + "-iteration-" + j + " removed " + key);
                        }
                    }
                }
                catch (Exception e){
                    System.err.println("Thread " + Thread.currentThread().getName() + " encountered an error: " + e.getMessage());
                }
            });
        }

        // Wait for all threads to complete
        System.out.println("Main thread starting shutdown");
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.out.println("Main thread waiting for shutdown");

            Thread.sleep(100);
        }
        System.out.println("Main thread completed shutdown");
        // Final consistency checks
        System.out.println("\n✅ Test Completed!");
        System.out.println("Final Size of Map: " + myMap.size());

    }


}
