package com.example.lld.ride.management.entities;

public class Rider extends User{

    private boolean isRiding = false;
    public Rider(String name) {
        super(name);
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }
}
