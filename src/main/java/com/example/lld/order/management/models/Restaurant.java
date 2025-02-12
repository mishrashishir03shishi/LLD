package com.example.lld.order.management.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private final String id = UUID.randomUUID().toString();

    private String name;

    private String address;

    private Menu menu;

    private final List<Order> orderList;


    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.orderList = new ArrayList<>();
        this.menu = new Menu(this.id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<FoodItem> getMenu(){
        return this.menu.getMenuItems();
    }

    public void addItemToMenu(FoodItem item){
        System.out.println("Adding food item :" + item.toString() + "to menu in restaurant " + this.name);
        this.menu.addFoodItem(item);
    }

    public void removeItemToMenu(FoodItem item){
        System.out.println("Removing food item :" + item.toString() + "to menu in restaurant " + this.name);
        this.menu.removeFoodItem(item);
    }

    public void addOrder(Order order){
        this.orderList.add(order);
        System.out.println("Starting to prepare order number : " + order.getId());
    }

    public List<Order> viewOrderList(){
        return Collections.unmodifiableList(this.orderList);
    }

    public List<FoodItem> serveFood(String orderId){
        System.out.println("Serving in restaurant : " +  this.name + " order number : " + orderId);
        return this.orderList.stream().filter(o -> o.getId().equals(orderId)).findFirst().orElseThrow().getItems();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
