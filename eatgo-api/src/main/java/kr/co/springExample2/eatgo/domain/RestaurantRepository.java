package kr.co.springExample2.eatgo.domain;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findAll();
    Restaurant findById(Long id);
}
