package com.example.lld.payment.services;

import com.example.lld.payment.discount.DiscountStrategy;
import com.example.lld.payment.paymentmethods.IPaymentMethod;
import com.example.lld.payment.user.User;

public class PaymentService {

    private DiscountStrategy discountStrategy;

    private final UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void payAmount(IPaymentMethod paymentMethod, String userId, int amount){
        User user = userService.getUserById(userId);
        int finalAmount = discountStrategy.applyDiscount(amount);
        paymentMethod.pay(finalAmount);
        user.showMessage("Payment made successfully for user " + user.getName());
    }
}
