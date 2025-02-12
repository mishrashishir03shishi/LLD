package com.example.lld.ride.management.search;

import com.example.lld.ride.management.entities.Driver;
import com.example.lld.ride.management.entities.Location;

import java.util.List;

public interface SearchDriverStrategy {

    String getDriver(List<Driver> driverList, Location startLocation);

}
