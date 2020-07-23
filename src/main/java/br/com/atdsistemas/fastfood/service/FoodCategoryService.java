package br.com.atdsistemas.fastfood.service;

import br.com.atdsistemas.fastfood.model.Food;
import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.category.CategoryDetailsDTO;
import br.com.atdsistemas.fastfood.model.dto.food.FoodDTO;
import br.com.atdsistemas.fastfood.model.dto.food_category.FoodCategoryDTO;
import br.com.atdsistemas.fastfood.repository.FoodCategoryRepository;
import br.com.atdsistemas.fastfood.repository.FoodRepository;
import br.com.atdsistemas.fastfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodCategoryService {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public CategoryDetailsDTO findById(long id, Pageable pageable){
        Optional<FoodCategory> category = foodCategoryRepository.findById(id);
        if (category.isPresent()){
            Page<Food> foodsList = foodRepository.findAllByFoodCategory_Id(id, pageable);
            Page<FoodDTO> foods = foodsList.map(FoodDTO::new);
            return new CategoryDetailsDTO(category.get(), foods);
        }
        return null;
    }

    public FoodCategoryDTO create(FoodCategory foodCategory, long restaurant_id) {
        Optional<Restaurant> byId = restaurantRepository.findById(restaurant_id);
        if (byId.isPresent()){
            Restaurant restaurant = byId.get();
            foodCategory.setRestaurant(restaurant);
            foodCategoryRepository.save(foodCategory);
            return new FoodCategoryDTO(foodCategory);
        }

        return null;
    }

    public FoodCategoryDTO updateImage(String upload, long id) {
        Optional<FoodCategory> byId = foodCategoryRepository.findById(id);
        if (byId.isPresent()){
            byId.get().setPicture(upload);
            return new FoodCategoryDTO(byId.get());
        }
        return null;
    }

    public FoodCategoryDTO update(String name, long id) {
        Optional<FoodCategory> byId = foodCategoryRepository.findById(id);
        if (byId.isPresent()){
            byId.get().setName(name);
            return new FoodCategoryDTO(byId.get());
        }
        return null;
    }

    public void delete(long id) {
        foodCategoryRepository.deleteById(id);
    }

}
