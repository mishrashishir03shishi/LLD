package com.example.lld.payment;

import com.example.lld.payment.discount.DiscountStrategy;
import com.example.lld.payment.discount.FixedDiscount;
import com.example.lld.payment.factories.UPIPaymentFactory;
import com.example.lld.payment.paymentmethods.IPaymentMethod;
import com.example.lld.payment.paymentmethods.UPIPayment;
import com.example.lld.payment.services.PaymentService;
import com.example.lld.payment.services.UserService;
import com.example.lld.payment.user.User;

public class PaymentProcessingPlatform {

    private static PaymentProcessingPlatform instance;

    private PaymentProcessingPlatform(){

    }

    public static PaymentProcessingPlatform getInstance(){
        if(instance==null){
            instance = new PaymentProcessingPlatform();
        }
        return instance;
    }

    public void run(){
        UserService userService = new UserService();
        User user = userService.newUser("Rakesh");

        UPIPayment upiPayment = (UPIPayment) UPIPaymentFactory.getInstance().getPaymentMethod();
        upiPayment.setUpiId("123@ybl");
        upiPayment.setUserId(user.getUserId());

        user.addPaymentMethod(upiPayment);

        PaymentService paymentService = new PaymentService(userService);
        DiscountStrategy discountStrategy = new FixedDiscount();
        paymentService.setDiscountStrategy(discountStrategy);

        paymentService.payAmount(upiPayment, user.getUserId(), 75);
    }
}
