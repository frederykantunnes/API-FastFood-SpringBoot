package br.com.atdsistemas.fastfood.service;

import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDTO;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDetailsDTO;
import br.com.atdsistemas.fastfood.repository.FoodServiceRepository;
import br.com.atdsistemas.fastfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodServiceService {

    @Autowired
    FoodServiceRepository foodServiceRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Page<FoodServiceDTO> findAll(Pageable pageable){
        Page<FoodService> list =  foodServiceRepository.findAll(pageable);
        return  list.map(FoodServiceDTO::new);
    }

    public FoodServiceDetailsDTO findFoodServiceDetails(Long id, Pageable pageable){
        Optional<FoodService> foodService = foodServiceRepository.findById(id);
        if (foodService.isPresent()){
            Page<Restaurant> list =  restaurantRepository.findAllByServices_Id(id, pageable);
            return new FoodServiceDetailsDTO(foodService.get(), list);
        }
        return null;
    }

    public FoodService create(FoodService foodService) {
        return foodServiceRepository.save(foodService);
    }

    public void delete(long id) {
        foodServiceRepository.deleteById(id);
    }

//    public FoodService create(FoodServiceForm foodService) {
//        FoodService service = foodService.to_convert();
//        foodServiceRepository.save(service);
//        return service;
//    }
}
