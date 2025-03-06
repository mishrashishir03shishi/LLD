package com.example.lld;

import com.example.lld.cache.CacheManagementPlatform;
import com.example.lld.hashmap.HashMapDriverPlatform;
import com.example.lld.instagram.Instagram;
import com.example.lld.instagram.Post;
import com.example.lld.instagram.User;
import com.example.lld.payment.PaymentProcessingPlatform;
import com.example.lld.ride.management.RideManagementPlatform;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

//        Instagram.run();
//        RideManagementPlatform.run();
//        CacheManagementPlatform.run();
//        PaymentProcessingPlatform.getInstance().run();
        HashMapDriverPlatform.getInstance().run();
    }
}