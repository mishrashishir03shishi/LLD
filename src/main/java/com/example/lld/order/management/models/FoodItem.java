package com.example.lld.order.management.models;

import java.util.UUID;

public class FoodItem {

    private final String id = UUID.randomUUID().toString();

    private final String name;

    private final Double price;

    public FoodItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
