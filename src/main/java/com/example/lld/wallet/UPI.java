package com.example.lld.wallet;

public class UPI implements PaymentMethod{

    private String upiId;

    public UPI() {
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Payment of Rs. " + amount + " successful via UPI");
    }
}
