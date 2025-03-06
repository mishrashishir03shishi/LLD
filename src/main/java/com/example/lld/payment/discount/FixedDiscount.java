package com.example.lld.payment.discount;

public class FixedDiscount implements DiscountStrategy{
    @Override
    public int applyDiscount(int initialAmount) {
        return initialAmount-50;
    }
}
