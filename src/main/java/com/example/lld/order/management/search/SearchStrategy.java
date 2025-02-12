package com.example.lld.order.management.search;

import com.example.lld.order.management.models.Restaurant;

import java.util.List;

public interface SearchStrategy {

    List<Restaurant> search(String searchTerm, List<Restaurant> globalRestaurantList);

}
