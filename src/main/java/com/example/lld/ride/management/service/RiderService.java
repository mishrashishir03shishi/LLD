package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Ride;
import com.example.lld.ride.management.entities.Rider;

import java.util.ArrayList;
import java.util.List;

public class RiderService {

    private final List<Rider> riderList = new ArrayList<>();

    public void addRider(Rider rider){
        System.out.println(String.format("Adding rider %s %s", rider.getId(), rider.getName()));
        this.riderList.add(rider);
    }

    public Rider getRiderByName(String name){
        System.out.println(String.format("Finding rider by name %s", name));
        return riderList
                .stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    protected Rider getRiderById(String id){
        System.out.println(String.format("Finding rider by id %s", id));
        return riderList
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow();

    }

    protected void startRide(String id, Ride ride){
        System.out.println(String.format("Starting ride in rider service for rider %s with ride id %s", id, ride.getId()));
        Rider rider = getRiderById(id);
        rider.setRiding(true);
        rider.addRide(ride);
    }

    protected void completeRide(String id){
        System.out.println(String.format("Completing ride in rider service for rider %s", id));
        Rider rider = getRiderById(id);
        rider.setRiding(false);
    }


}
