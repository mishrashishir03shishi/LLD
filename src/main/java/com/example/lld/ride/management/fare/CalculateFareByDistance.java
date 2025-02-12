package com.example.lld.ride.management.fare;

import com.example.lld.ride.management.entities.Location;

public class CalculateFareByDistance implements FareCalculationStrategy{

    @Override
    public int calculateFare(Location startLocation, Location endLocation) {
        final int baseFare = 20;
        return baseFare + Math.abs(startLocation.getLocation()-endLocation.getLocation())*2;
    }
}
