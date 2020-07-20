package br.com.atdsistemas.fastfood.model.dto.food_service;

import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public class FoodServiceDetailsDTO {

    private Long id;
    private String name;
    private Page<Restaurant> restaurants;

    public FoodServiceDetailsDTO(FoodService foodService, Page<Restaurant> restaurants) {
        this.id = foodService.getId();
        this.name = foodService.getName();
        this.restaurants = restaurants;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Page<Restaurant> getRestaurants() {
        return restaurants;
    }
}
