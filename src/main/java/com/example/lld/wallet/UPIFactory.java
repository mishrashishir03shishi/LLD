package com.example.lld.wallet;

public class UPIFactory implements PaymentMethodFactory{
    @Override
    public PaymentMethod getPaymentMethod() {
        return new UPI();
    }
}
