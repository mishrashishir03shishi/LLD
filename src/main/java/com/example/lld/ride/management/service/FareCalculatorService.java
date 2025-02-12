package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.fare.FareCalculationStrategy;

public class FareCalculatorService {

    private FareCalculationStrategy fareCalculationStrategy;

    public FareCalculatorService(FareCalculationStrategy fareCalculationStrategy) {
        this.fareCalculationStrategy = fareCalculationStrategy;
    }

    protected int calculateFare(Location startLocation, Location endLocation){
        System.out.println("Calculating fare in fare calculation strategy service");
        return fareCalculationStrategy.calculateFare(startLocation, endLocation);
    }
}
