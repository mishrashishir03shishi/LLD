package com.example.lld.order.management.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {

    private final String userId;

    private final List<FoodItem> menuItems;


    public Menu(String userId) {
        this.userId = userId;
        this.menuItems = new ArrayList<>();
    }

    public void addFoodItem(FoodItem foodItem){
        this.menuItems.add(foodItem);
    }

    public void removeFoodItem(FoodItem item){
        this.menuItems.remove(item);
    }

    public List<FoodItem> getMenuItems(){
        return Collections.unmodifiableList(menuItems);
    }
}
