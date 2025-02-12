package com.example.lld.order.management.service;

import com.example.lld.order.management.payment.PaymentStrategy;

public class PaymentService {

    private PaymentStrategy paymentStrategy;


    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payBill(Double amount){
        if(this.paymentStrategy==null){
            throw new RuntimeException("Payment Strategy can't be null");
        }
        String userId = paymentStrategy.getUserId();
        System.out.println("Payment of " + amount + " made using " + paymentStrategy.getClass().getSimpleName() + " by user id " + userId);
    }
}
