package com.example.lld.payment.paymentmethods;

public class UPIPayment implements IPaymentMethod{

    private String upiId;

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public void pay(int amount) {
        System.out.println(String.format("Payment made using upiId : %s for amount : %d successfully", upiId, amount));
    }
}
