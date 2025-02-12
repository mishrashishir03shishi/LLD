package com.example.lld.order.management.service;

import com.example.lld.order.management.models.Order;
import com.example.lld.order.management.models.User;
import com.example.lld.order.management.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> userList = new ArrayList<>();

    private static UserService instance;

    public User addUser(String name, String contact){
        User user = new User(name, contact);
        System.out.println("Adding new user with name : " + name);
        userList.add(user);
        return user;
    }

    public static UserService getInstance(){
        if(instance==null){
            instance = new UserService();
        }
        return instance;
    }

    public void removeUser(User user){
        System.out.println("Removing new user with name : " + user.getName());
        userList.remove(user);
    }

    protected User getUserById(String userId){
        System.out.println("Finding new user with id : " + userId);
        return this.userList.stream().filter(u -> u.getId().equals(userId)).findFirst().orElseThrow();
    }

    protected void addOrder(String userId, Order order){
        System.out.println("Adding order with id : " + order.getId() + " for user id : " + userId);
        getUserById(userId).addOrder(order);
    }

    public void addPaymentMethod(String userId, PaymentStrategy strategy){
        System.out.println("Adding payment method for user id : " + userId);
        getUserById(userId).addPaymentStrategy(strategy);
    }

}
