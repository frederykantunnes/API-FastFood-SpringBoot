package br.com.atdsistemas.fastfood.service;

import br.com.atdsistemas.fastfood.model.Address;
import br.com.atdsistemas.fastfood.model.Food;
import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.food_category.FoodCategoryDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.restaurant.RestaurantFormEdit;
import br.com.atdsistemas.fastfood.repository.AddressRepository;
import br.com.atdsistemas.fastfood.repository.FoodCategoryRepository;
import br.com.atdsistemas.fastfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @Autowired
    AddressRepository addressRepository;

    public Page<RestaurantDTO> findAll(Pageable pageable){
        return restaurantRepository.findAll(pageable).map(RestaurantDTO::new);
    }

    public RestaurantDetailsDTO findById(long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            RestaurantDetailsDTO restaurantDetailsDTO = new RestaurantDetailsDTO(restaurant.get(), getCategories(id));
            return restaurantDetailsDTO;
        }
        return null;
    }

    public RestaurantDetailsDTO create(Restaurant restaurant){
        Address address = addressRepository.save(restaurant.getAddress());
        restaurant.setAddress(address);
        Restaurant save = restaurantRepository.save(restaurant);
        RestaurantDetailsDTO restaurantDetailsDTO = new RestaurantDetailsDTO(save, getCategories(save.getId()));
        return restaurantDetailsDTO;
    }

    public RestaurantDetailsDTO update(RestaurantFormEdit restaurantFormEdit, long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            Restaurant updateRest = restaurant.get();
            Optional<Address> address = addressRepository.findById(updateRest.getAddress().getId());
            Address addressUpdate = null;
            if (address.isPresent()){
                addressUpdate = address.get();
                addressUpdate.setZipcode(restaurantFormEdit.getZipcode());
                addressUpdate.setStreet(restaurantFormEdit.getStreet());
                addressUpdate.setNumber(restaurantFormEdit.getNumber());
                addressUpdate.setNeighborhood(restaurantFormEdit.getNeighborhood());
                addressUpdate.setReference_point(restaurantFormEdit.getReference_point());
                addressUpdate.setCity(restaurantFormEdit.getCity());
            }
            updateRest.setPhone(restaurantFormEdit.getPhone());
            updateRest.setDetails(restaurantFormEdit.getDetails());
            updateRest.setOpening_hours(restaurantFormEdit.getOpening_hours());
            return new RestaurantDetailsDTO(updateRest, getCategories(updateRest.getId()));
        }
        return null;
    }


    public List<FoodCategoryDTO> getCategories(long id){
        return foodCategoryRepository.findAllByRestaurant_Id(id).stream().map(FoodCategoryDTO::new).collect(Collectors.toList());
    }


    public RestaurantDetailsDTO updateImage(String upload, long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            Restaurant rest = restaurant.get();
            rest.setPicture(upload);
            return new RestaurantDetailsDTO(rest, getCategories(rest.getId()));
        }
        return null;
    }

    public RestaurantDetailsDTO updateOpenClosed(boolean is_open, long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            Restaurant rest = restaurant.get();
            rest.setIs_open(is_open);
            return new RestaurantDetailsDTO(rest, getCategories(rest.getId()));
        }
        return null;
    }
}
