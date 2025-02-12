package com.example.lld.order.management.search;

import com.example.lld.order.management.models.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByName implements SearchStrategy{
    @Override
    public List<Restaurant> search(String searchTerm, List<Restaurant> globalRestaurantList) {
        return globalRestaurantList.stream().filter(r -> r.getName().contains(searchTerm)).collect(Collectors.toList());
    }
}
