package br.com.atdsistemas.fastfood.service;

import br.com.atdsistemas.fastfood.model.Additional;
import br.com.atdsistemas.fastfood.model.Food;
import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.food.FoodDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.food.FoodFormUpdate;
import br.com.atdsistemas.fastfood.repository.AdditionalRepository;
import br.com.atdsistemas.fastfood.repository.FoodCategoryRepository;
import br.com.atdsistemas.fastfood.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceFood {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    AdditionalRepository additionalRepository;

    public FoodDTO create(Food food, long category) {
        Optional<FoodCategory> byId = foodCategoryRepository.findById(category);
        if (byId.isPresent()){
            food.setFoodCategory(byId.get());
            foodRepository.save(food);
            return new FoodDTO(food);
        }
        return null;
    }

    public FoodDTO update(FoodFormUpdate formUpdate, long id) {
        Optional<Food> byId = foodRepository.findById(id);
        if (byId.isPresent()){
            Food food = byId.get();
            food.setIs_featured(formUpdate.isIs_featured());
            food.setPrice(formUpdate.getPrice());
            food.setPreparation_time(formUpdate.getPreparation_time());
            food.setIs_combo(formUpdate.isIs_combo());
            food.setDetails(formUpdate.getDetails());
            food.setAvailable(formUpdate.isAvailable());
            food.setName(formUpdate.getName());
            return new FoodDTO(food);
        }
        return null;
    }

    public FoodDTO updateImage(String upload, long id) {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if (foodOptional.isPresent()){
            Food food = foodOptional.get();
            food.setPicture(upload);
            return new FoodDTO(food);
        }
        return null;
    }

    public void delete(long id) {
        foodRepository.deleteById(id);
    }

    public FoodDTO addAditionals(long id, long additional) {
        Optional<Additional> additionalOptional = additionalRepository.findById(additional);
        if (additionalOptional.isPresent()){
            Optional<Food> food = foodRepository.findById(id);
            if (food.isPresent()){
                Food foodPresent = food.get();
                List<Additional> additionals = foodPresent.getAdditionals();
                additionals.add(additionalOptional.get());
                foodPresent.setAdditionals(additionals);
                return new FoodDTO(foodPresent);
            }
        }
        return null;
    }

    public void deleteAdditional(long id, long additional) {
        Optional<Additional> additionalOptional = additionalRepository.findById(additional);
        if (additionalOptional.isPresent()){
            Optional<Food> food = foodRepository.findById(id);
            if (food.isPresent()){
                Food foodPresent = food.get();
                List<Additional> additionals = foodPresent.getAdditionals();
                additionals.remove(additionalOptional.get());
                foodPresent.setAdditionals(additionals);
            }
        }
    }
}
