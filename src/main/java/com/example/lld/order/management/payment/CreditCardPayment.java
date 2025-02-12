package com.example.lld.order.management.payment;

public class CreditCardPayment extends PaymentStrategy{

    private final String cardNumber;

    private final String expiry;

    private final String cvv;

    public CreditCardPayment(String userId, String cardNumber, String expiry, String cvv) {
        super(userId);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }


    @Override
    public void pay(Double amount) {
        System.out.println("Credit Card : Number : " + this.cardNumber + " expiry : " + this.expiry + " cvv : " + this.cvv);
        System.out.println("Payment made via credit card of amount : " + amount + " by user : " + this.getUserId());
    }


}
