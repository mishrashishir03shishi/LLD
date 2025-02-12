package com.example.lld.order.management.payment;

public class UpiPayment extends PaymentStrategy{

    private final String upiId;

    private final String upiPin;

    public UpiPayment(String userId, String upiId, String upiPin) {
        super(userId);
        this.upiId = upiId;
        this.upiPin = upiPin;
    }

    @Override
    public void pay(Double amount) {
        System.out.println("Upi Payment : upiId : " + this.upiId);
        System.out.println("Payment made via upi of amount : " + amount + " by user : " + this.getUserId());
    }
}
