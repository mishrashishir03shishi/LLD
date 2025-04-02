package com.example.lld.wallet;

public class CreditCardFactory implements PaymentMethodFactory{
    @Override
    public PaymentMethod getPaymentMethod() {
        return new CreditCard();
    }
}
