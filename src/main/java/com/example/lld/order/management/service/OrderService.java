package com.example.lld.order.management.service;

import com.example.lld.order.management.models.FoodItem;
import com.example.lld.order.management.models.Order;
import com.example.lld.order.management.models.OrderStatus;

import java.util.List;

public class OrderService {

    private final RestaurantService restaurantService;

    private final UserService userService;

    public OrderService(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public Order createOrder(String userId, String restaurantId, List<FoodItem> foodItemList) throws InterruptedException {
        System.out.println("Creating new order for user " + userId + " and restaurant " + restaurantId);
        Order order = new Order(userId, restaurantId, foodItemList);
        updateOrderStatus(order, OrderStatus.ORDERED);
        restaurantService.startFoodPreparation(restaurantId, order);
        userService.addOrder(userId, order);
        Thread.sleep(5000L);
        updateOrderStatus(order, OrderStatus.PREPARING);
        return order;
    }

    public void updateOrderStatus(Order order, OrderStatus orderStatus){
        System.out.println("Updating order status to : " +  orderStatus.name() + " for order id : " + order.getId());
        order.updateOrderStatus(orderStatus);
    }

    public void serveOrder(String restaurantId, Order order){
        System.out.println("Updating order status to : " +  OrderStatus.SERVED.name() + " for order id : " + order.getId());
        List<FoodItem> itemList = restaurantService.completeOrder(restaurantId, order.getId());
        System.out.println("Serving order now");
        itemList.forEach(System.out::println);
        order.updateOrderStatus(OrderStatus.SERVED);
    }

    public void closeOrder(Order order){
        System.out.println("Closing order : " + order.getId());
        order.updateOrderStatus(OrderStatus.COMPLETED);
    }
}
