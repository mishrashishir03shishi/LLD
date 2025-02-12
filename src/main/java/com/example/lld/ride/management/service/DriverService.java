package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Driver;
import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.entities.Ride;
import com.example.lld.ride.management.entities.Rider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverService {

    private final List<Driver> driverList = new ArrayList<>();

    public void addNewDriver(Driver driver){
        System.out.println(String.format("Constructor called in driver service"));
        this.driverList.add(driver);
    }

    protected Driver getDriverById(String id){
        System.out.println(String.format("Finding driver by id %s", id));
        return this.driverList
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    protected List<Driver> getAvailableDrivers(){
        System.out.println(String.format("Getting all available drivers in driver service"));
        return driverList
                .stream()
                .filter(Driver::isAvailable)
                .collect(Collectors.toList());
    }

    protected void startRide(String id, Ride ride){
        System.out.println(String.format("Starting ride in driver service for driver %s with ride id %s", id, ride.getId()));
        Driver driver = getDriverById(id);
        driver.setAvailable(false);
        driver.addRide(ride);
    }

    protected void endRide(String id, Location endLocation){
        System.out.println(String.format("Ending ride in driver service for driver %s ", id));
        Driver driver = getDriverById(id);
        driver.setAvailable(true);
        driver.setEndLocation(endLocation);
    }
}
