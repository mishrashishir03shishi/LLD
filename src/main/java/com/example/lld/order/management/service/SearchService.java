package com.example.lld.order.management.service;

import com.example.lld.order.management.models.Restaurant;
import com.example.lld.order.management.search.SearchStrategy;

import java.util.List;

public class SearchService {

    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Restaurant> searchRestaurant(String searchTerm, List<Restaurant> globalRestaurantList){
        if(this.searchStrategy==null){
            throw new RuntimeException("Search Strategy can't be null");
        }
        System.out.println("Searching the catalog : " + this.searchStrategy.getClass().getSimpleName() + " with search term : " + searchTerm);
        return searchStrategy.search(searchTerm, globalRestaurantList);
    }
}
