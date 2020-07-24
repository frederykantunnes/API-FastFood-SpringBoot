package br.com.atdsistemas.fastfood.service;

import br.com.atdsistemas.fastfood.model.Additional;
import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.additional.AdditionalDTO;
import br.com.atdsistemas.fastfood.model.dto.food_category.FoodCategoryDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDetailsDTO;
import br.com.atdsistemas.fastfood.repository.AdditionalRepository;
import br.com.atdsistemas.fastfood.repository.FoodCategoryRepository;
import br.com.atdsistemas.fastfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdditionalService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AdditionalRepository additionalRepository;

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    public RestaurantDetailsDTO createAdditional(Additional additional, long id_restaurant){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id_restaurant);
        if (restaurantOptional.isPresent()){
            additional.setRestaurant(restaurantOptional.get());
            additionalRepository.save(additional);
            List<FoodCategoryDTO> category = foodCategoryRepository.findAllByRestaurant_Id(id_restaurant).stream().map(FoodCategoryDTO::new).collect(Collectors.toList());
            List<AdditionalDTO> additionals = additionalRepository.findAllByRestaurant_Id(id_restaurant).stream().map(AdditionalDTO::new).collect(Collectors.toList());
            return new RestaurantDetailsDTO(restaurantOptional.get(), category, additionals);
        }
        return null;
    }

    public void deleteAdditional(long id) {
        additionalRepository.deleteById(id);
    }
}
