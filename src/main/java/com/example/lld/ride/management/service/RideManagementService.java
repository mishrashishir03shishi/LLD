package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.entities.Ride;

public class RideManagementService {

    private final RiderService riderService;

    private final DriverService driverService;

    private final SearchService searchService;

    private FareCalculatorService fareCalculatorService;

    private final RideService rideService;

    private PaymentService paymentService;


    public RideManagementService(RiderService riderService, DriverService driverService, RideService rideService, SearchService searchService) {
        System.out.println("Initialised new RideService");
        this.riderService = riderService;
        this.driverService = driverService;
        this.rideService = rideService;
        this.searchService = searchService;
    }


    public void setFareCalculatorService(FareCalculatorService fareCalculatorService) {
        this.fareCalculatorService = fareCalculatorService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public synchronized Ride startNewRide(String riderId, Location startLocation, Location endLocation){
        System.out.println("Finding driver in ride management service");
        String driverId = searchService.searchDriver(driverService.getAvailableDrivers(), startLocation);
        System.out.println(String.format("Found driver in ride management service with id %s", driverId));
        Ride ride = new Ride(riderId, driverId, startLocation, endLocation);
        System.out.println(String.format("Initialized new Ride"));
        riderService.startRide(riderId, ride);
        driverService.startRide(driverId, ride);
        rideService.addRide(ride);
        return ride;
    }

    public synchronized void completeRide(String rideId){
        System.out.println(String.format("Completed ride with id %s", rideId));
        Ride ride = rideService.completeRide(rideId);
        riderService.completeRide(ride.getRiderId());
        driverService.endRide(ride.getDriverId(), ride.getEndLocation());
    }

    public int calculateFare(String rideId){
        System.out.println(String.format("Calculating fare for ride with id %s", rideId));
        Ride ride = rideService.getRideById(rideId);
        return fareCalculatorService.calculateFare(ride.getStartLocation(), ride.getEndLocation());
    }

    public void makePayment(String rideId, int amount){
        System.out.println(String.format("Paying fare for ride with id %s for amount %d in ride management service", rideId, amount));
        rideService.updateFare(rideId, amount);
        paymentService.makePayment(amount);
    }

}
