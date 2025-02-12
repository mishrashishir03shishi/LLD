package com.example.lld.ride.management.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class User {

    private final String id = UUID.randomUUID().toString();

    private final String name;

    private final List<Ride> rideList = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ride> getRideList() {
        return Collections.unmodifiableList(rideList);
    }

    public void addRide(Ride ride){
        this.rideList.add(ride);
    }
}
