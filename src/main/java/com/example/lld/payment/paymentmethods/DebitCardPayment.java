package com.example.lld.payment.paymentmethods;

public class DebitCardPayment implements IPaymentMethod{

    public String cardNumber;

    public String name;

    public String expiry;

    public String userId;


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void pay(int amount) {
        System.out.println(String.format("Payment made using debit cardNumber : %s for amount : %d successfully", cardNumber, amount));
    }
}
