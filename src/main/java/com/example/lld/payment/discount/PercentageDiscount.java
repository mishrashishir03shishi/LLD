package com.example.lld.payment.discount;

public class PercentageDiscount implements DiscountStrategy{
    @Override
    public int applyDiscount(int initialAmount) {
        return (int) (0.9*initialAmount);
    }
}
