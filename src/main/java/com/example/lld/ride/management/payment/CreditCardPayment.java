package com.example.lld.ride.management.payment;

public class CreditCardPayment implements PaymentStrategy{

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void makePayment(int paymentAmount) {
        System.out.println(String.format("Payment made via credit card %s for amount %d successfully", cardNumber, paymentAmount));
    }
}
