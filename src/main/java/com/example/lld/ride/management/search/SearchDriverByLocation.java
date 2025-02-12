package com.example.lld.ride.management.search;

import com.example.lld.ride.management.entities.Driver;
import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.entities.User;

import java.util.List;

public class SearchDriverByLocation implements SearchDriverStrategy{
    @Override
    public String getDriver(List<Driver> driverList, Location startLocation) {
        return driverList
                .stream()
                .filter(d -> Math.abs(d.getEndLocation().getLocation()- startLocation.getLocation())<10)
                .map(User::getId)
                .findFirst()
                .orElseThrow();
    }
}
