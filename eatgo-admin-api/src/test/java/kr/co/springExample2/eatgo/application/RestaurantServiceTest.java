package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;


    private void MockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").categoryId(1L).build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));


    }


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

//        restaurantRepository = new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();

        MockRestaurantRepository();
        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();


        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }
    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurantById(1004L);

        assertThat(restaurant.getId(),is(1004L));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        Restaurant restaurant = restaurantService.getRestaurantById(404L);
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });
        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();


        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);


        assertThat(createdRestaurant.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                                        .id(1234L)
                                        .name("Bob zip")
                                        .address("Seoul")
                                        .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L,"Sool zip","Busan" );

        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(),is("Busan"));
    }


}