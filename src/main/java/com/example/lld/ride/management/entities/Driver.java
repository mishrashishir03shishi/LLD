package com.example.lld.ride.management.entities;

public class Driver extends User {

    private boolean isAvailable = true;
    private Location endLocation;

    public Driver(String name, int location) {
        super(name);
        this.endLocation = new Location(location);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Location getEndLocation() {
        return new Location(endLocation.getLocation());
    }
}
