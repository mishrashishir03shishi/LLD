package com.example.lld.ride.management.service;

import com.example.lld.ride.management.payment.PaymentStrategy;

public class PaymentService {

    private final PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    protected void makePayment(int amount){
        System.out.println(String.format("Making payment in Payment service of amount %d", amount));
        paymentStrategy.makePayment(amount);
    }
}
