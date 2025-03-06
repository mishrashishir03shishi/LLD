package com.example.lld.payment.user;

import com.example.lld.payment.paymentmethods.IPaymentMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Observer{

    private final String userId = UUID.randomUUID().toString();

    private final String name;

    private final List<IPaymentMethod> paymentMethods;

    public User(String name) {
        this.name = name;
        paymentMethods = new ArrayList<>();
    }

    public void addPaymentMethod(IPaymentMethod paymentMethod){
        paymentMethods.add(paymentMethod);
    }


    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
