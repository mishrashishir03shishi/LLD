package com.example.lld.payment.factories;

import com.example.lld.payment.paymentmethods.DebitCardPayment;
import com.example.lld.payment.paymentmethods.IPaymentMethod;

public class DebitCardPaymentFactory implements PaymentMethodFactory{

    private static DebitCardPaymentFactory instance;

    private DebitCardPaymentFactory() {
    }

    private static DebitCardPaymentFactory getInstance(){
        if(instance==null){
            instance = new DebitCardPaymentFactory();
        }
        return instance;
    }

    @Override
    public IPaymentMethod getPaymentMethod() {
        return new DebitCardPayment();
    }
}
