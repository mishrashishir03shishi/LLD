package com.example.lld.payment.factories;

import com.example.lld.payment.paymentmethods.CreditCardPayment;
import com.example.lld.payment.paymentmethods.IPaymentMethod;

public class CreditCardPaymentFactory implements PaymentMethodFactory{

    private static CreditCardPaymentFactory instance;

    private CreditCardPaymentFactory(){
    }

    public static CreditCardPaymentFactory getInstance(){
        if(instance==null){
            instance = new CreditCardPaymentFactory();
        }
        return instance;
    }
    @Override
    public IPaymentMethod getPaymentMethod() {
        return new CreditCardPayment();
    }
}
