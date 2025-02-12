package com.example.lld.ride.management.entities;

import java.util.UUID;
import static java.lang.System.out;

public class Ride {

    private final String id = UUID.randomUUID().toString();

    private final String riderId;

    private final String driverId;

    private final Location startLocation;

    private final Location endLocation;

    private RideStatus rideStatus;

    private Integer totalFare = 0;

    public Ride(String riderId, String driverId, Location startLocation, Location endLocation) {
        out.println(String.format("Starting new Ride riderId %s , driverId %s, start %d, end %d, status %s", riderId, driverId, startLocation.getLocation(), endLocation.getLocation(), RideStatus.STARTED.name()));
        this.riderId = riderId;
        this.driverId = driverId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.rideStatus = RideStatus.STARTED;
    }

    public String getId() {
        return id;
    }

    public String getRiderId() {
        return riderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public Location getStartLocation() {
        return new Location(startLocation.getLocation());
    }

    public Location getEndLocation() {
        return new Location(endLocation.getLocation());
    }

    public RideStatus getRideStatus() {
        return RideStatus.valueOf(rideStatus.name());
    }

    public void updateRideStatus(RideStatus rideStatus){
        this.rideStatus = rideStatus;
    }

    public void setTotalFare(Integer totalFare) {
        this.totalFare = totalFare;
    }
}
