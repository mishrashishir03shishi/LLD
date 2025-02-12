package com.example.lld.order.management.payment;

public abstract class PaymentStrategy {

    private final String userId;

    public PaymentStrategy(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public abstract void pay(Double amount);
}
