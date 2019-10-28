package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.RestaurantService;
import kr.co.springExample2.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping("restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {

        Restaurant restaurant = Restaurant.builder().name(resource.getName()).address(resource.getAddress()).build();
        restaurantService.addRestaurant(restaurant);
        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }


    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurant;
    }

    @PutMapping("/restaurants/{id}")
    public String update(@PathVariable Long id,@Valid @RequestBody Restaurant request) {
        String name = request.getName();
        String address = request.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }

}
