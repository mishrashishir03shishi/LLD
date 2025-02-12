package com.example.lld.ride.management.service;

import com.example.lld.ride.management.entities.Driver;
import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.search.SearchDriverStrategy;

import java.util.List;

public class SearchService {

    private SearchDriverStrategy searchDriverStrategy;

    public SearchService(SearchDriverStrategy searchDriverStrategy) {
        System.out.println("Calling Constructor in search driver service");
        this.searchDriverStrategy = searchDriverStrategy;
    }

    protected String searchDriver(List<Driver> driverList, Location startLocation){
        System.out.println(String.format("Searching driver in service near location %d ", startLocation.getLocation()));
        return searchDriverStrategy.getDriver(driverList, startLocation);
    }
}
