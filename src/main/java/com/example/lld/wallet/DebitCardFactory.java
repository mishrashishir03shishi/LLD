package com.example.lld.wallet;

public class DebitCardFactory implements PaymentMethodFactory{
    @Override
    public PaymentMethod getPaymentMethod() {
        return new DebitCard();
    }
}
