package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Ride;
import com.example.lld.ride.management.entities.RideStatus;

import java.util.ArrayList;
import java.util.List;

public class RideService {

    private final List<Ride> rideList = new ArrayList<>();

    protected void addRide(Ride ride){
        System.out.println(String.format("Adding ride in rideService %s", ride.getId()));
        rideList.add(ride);
    }

    protected Ride getRideById(String id){
        System.out.println(String.format("Finding ride with id %s", id));
        return this.rideList
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    protected Ride completeRide(String rideId){
        System.out.println(String.format("Completing ride with id %s in ride service", rideId));
        Ride ride = getRideById(rideId);
        ride.updateRideStatus(RideStatus.COMPLETED);
        return ride;
    }

    protected void updateFare(String id, Integer amount){
        System.out.println(String.format("Updating fare for ride with id %s in ride service for amount %d ", id, amount));
        Ride ride  = getRideById(id);
        ride.setTotalFare(amount);
    }

}
