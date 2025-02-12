package com.example.lld.order.management.models;

import com.example.lld.order.management.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User {

    private final String id = UUID.randomUUID().toString();

    private String name;

    private String contact;

    private final List<Order> orderList;

    private final List<PaymentStrategy> paymentStrategies;

    public User(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.orderList = new ArrayList<>();
        this.paymentStrategies = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getContact() {
        return contact;
    }

    public List<Order> getOrderList(){
        return Collections.unmodifiableList(orderList);
    }

    public List<PaymentStrategy> getPaymentStrategies(){
        return Collections.unmodifiableList(paymentStrategies);
    }

    public void addOrder(Order order){
        this.orderList.add(order);
    }

    public void addPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategies.add(paymentStrategy);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }
}
