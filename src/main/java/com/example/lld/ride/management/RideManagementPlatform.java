package com.example.lld.ride.management;

import com.example.lld.ride.management.entities.Driver;
import com.example.lld.ride.management.entities.Location;
import com.example.lld.ride.management.entities.Ride;
import com.example.lld.ride.management.entities.Rider;
import com.example.lld.ride.management.fare.CalculateFareByDistance;
import com.example.lld.ride.management.fare.FareCalculationStrategy;
import com.example.lld.ride.management.payment.CreditCardPayment;
import com.example.lld.ride.management.payment.PaymentStrategy;
import com.example.lld.ride.management.search.SearchDriverByLocation;
import com.example.lld.ride.management.search.SearchDriverStrategy;
import com.example.lld.ride.management.service.*;

import java.util.Random;

public class RideManagementPlatform {


    public static void run() throws InterruptedException {
        RiderService riderService = new RiderService();

        //Create Riders
        for(int i=0; i<10; i++){
            Rider rider = new Rider("Rider " + i);
            System.out.println("Rider generated : " + rider.getName());
            riderService.addRider(rider);
        }

        DriverService driverService = new DriverService();

        //Create Drivers
        Random random = new Random();
        for(int i=0; i<10; i++){
            Driver driver = new Driver("Driver " + i, random.nextInt(50) + 1);
            System.out.println("Driver generated : " + driver.getName() + " Location : " + driver.getEndLocation());
            driverService.addNewDriver(driver);
        }

        //Request a ride
        RideService rideService = new RideService();
        SearchDriverStrategy searchDriverStrategy = new SearchDriverByLocation();
        SearchService searchService = new SearchService(searchDriverStrategy);
        RideManagementService rideManagementService = new RideManagementService(riderService, driverService, rideService, searchService);


        //Pic a random rider
        int riderNumber = random.nextInt(10) + 1;
        System.out.println("Rider number : " + riderNumber);
        // Pick random start and end location
        int startLocation = random.nextInt(25) + 1;
        Location start = new Location(startLocation);
        int endLocation = startLocation + 25;
        Location end = new Location(endLocation);

        System.out.println(String.format("Start and end location %d %d", startLocation, endLocation));


        Ride ride = rideManagementService.startNewRide(riderService.getRiderByName("Rider " + riderNumber).getId(), start, end);

        Thread.sleep(5000L);

        //Complete the ride;
        rideManagementService.completeRide(ride.getId());

        Thread.sleep(500L);

        // Calculate the fare
        FareCalculationStrategy fareCalculationStrategy = new CalculateFareByDistance();
        FareCalculatorService fareCalculatorService = new FareCalculatorService(fareCalculationStrategy);

        rideManagementService.setFareCalculatorService(fareCalculatorService);

        int amount = rideManagementService.calculateFare(ride.getId());

        Thread.sleep(5000L);
        //Make Payment

        PaymentStrategy paymentStrategy = new CreditCardPayment("3265-0974");
        PaymentService paymentService = new PaymentService(paymentStrategy);
        rideManagementService.setPaymentService(paymentService);

        rideManagementService.makePayment(ride.getId(), amount);


    }
}
