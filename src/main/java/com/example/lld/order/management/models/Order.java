package com.example.lld.order.management.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id = UUID.randomUUID().toString();

    private String userId;

    private String restaurantId;

    private List<FoodItem> items;

    private OrderStatus orderStatus;

    private Date orderTime;

    public Order(String userId, String restaurantId, List<FoodItem> items) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.orderTime = Calendar.getInstance().getTime();
    }

    public void updateOrderStatus(OrderStatus newOrderStatus){
        this.orderStatus = newOrderStatus;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public String getId() {
        return id;
    }
}
