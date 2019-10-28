package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->new RestaurantNotFoundException(id));

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.setName(name);
        restaurant.setAddress(address);
        //restaurantRepository.save(restaurant);

        return restaurant;
    }
}
