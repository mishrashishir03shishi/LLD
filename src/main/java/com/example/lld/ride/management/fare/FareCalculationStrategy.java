package com.example.lld.ride.management.fare;

import com.example.lld.ride.management.entities.Location;

public interface FareCalculationStrategy {

    int calculateFare(Location startLocation, Location endLocation);
}
