package br.com.atdsistemas.fastfood.service;


import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.User;
import br.com.atdsistemas.fastfood.repository.RestaurantRepository;
import br.com.atdsistemas.fastfood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;


    public User create(User user, long restaurant_id){

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant_id);
        if (restaurantOptional.isPresent()){
            user.setRestaurant(restaurantOptional.get());
            user.setIs_blocked(false);
            userRepository.save(user);
            return user;
        }
        return user;
    }


}
