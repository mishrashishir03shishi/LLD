package com.example.lld.payment.factories;

import com.example.lld.payment.paymentmethods.IPaymentMethod;
import com.example.lld.payment.paymentmethods.UPIPayment;

public class UPIPaymentFactory implements PaymentMethodFactory{

    private static UPIPaymentFactory instance;

    private UPIPaymentFactory() {
    }

    public static UPIPaymentFactory getInstance(){
        if(instance==null){
            instance = new UPIPaymentFactory();
        }
        return instance;
    }

    @Override
    public IPaymentMethod getPaymentMethod() {
        return new UPIPayment();
    }
}
