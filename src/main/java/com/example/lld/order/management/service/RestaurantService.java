package com.example.lld.order.management.service;

import com.example.lld.order.management.models.FoodItem;
import com.example.lld.order.management.models.Order;
import com.example.lld.order.management.models.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantService {

    private final List<Restaurant> restaurantList = new ArrayList<>();

    public Restaurant addRestaurant(String name, String address){
        Restaurant restaurant =  new Restaurant(name, address);
        System.out.println("Adding new restaurant : " + restaurant.toString());
        this.restaurantList.add(restaurant);
        return restaurant;
    }

    public void removeRestaurant(Restaurant restaurant){
        System.out.println("Removing restaurant : " + restaurant.toString());
        this.restaurantList.remove(restaurant);
    }

    protected Restaurant getRestaurant(String id){
        System.out.println("Finding restaurant by id : " + id);
        return this.restaurantList.stream().filter(r -> r.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Restaurant> viewAllRestaurants(){
        System.out.println("Retrieving list of all restaurants : ");
        return Collections.unmodifiableList(restaurantList);
    }

    protected void startFoodPreparation(String restaurantId, Order order){
        System.out.println("Starting food preparation for order number : " + order.getId());
        this.getRestaurant(restaurantId).addOrder(order);
    }

    protected List<FoodItem> completeOrder(String restaurantId, String orderId){
        Restaurant restaurant = getRestaurant(restaurantId);
        System.out.println("Completing and serving order in restaurant " + restaurant.getName() + " with order id " + orderId);
        return restaurant.serveFood(orderId);
    }

    public void addFoodItemToMenu(String restaurantId, FoodItem foodItem){
        System.out.println("Adding food item to menu");
        getRestaurant(restaurantId).addItemToMenu(foodItem);
    }

    public void removeFoodItemToMenu(String restaurantId, FoodItem foodItem){
        System.out.println("Removing food item to menu");
        getRestaurant(restaurantId).removeItemToMenu(foodItem);
    }


    public void displayMenu(String restaurantId){
        Restaurant restaurant = getRestaurant(restaurantId);
        System.out.println("Displaying menu for : " + restaurant.getName());
        for(FoodItem item : restaurant.getMenu()){
            System.out.println(item.toString());
        }
    }
}
