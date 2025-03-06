package com.example.lld.payment.factories;

import com.example.lld.payment.paymentmethods.IPaymentMethod;

public interface PaymentMethodFactory {

    IPaymentMethod getPaymentMethod();
}
