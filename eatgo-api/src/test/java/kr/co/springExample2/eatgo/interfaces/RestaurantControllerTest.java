package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.RestaurantService;
import kr.co.springExample2.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));

    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        Restaurant restaurant2 = Restaurant.builder()
                .id(2020L)
                .name("Cyber Food")
                .address("Seoul")
                .build();
        MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));
        given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurantById(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"") ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("Seoul")
                .build();

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"BeRyong\", \"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
              //  .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {

        mvc.perform(put("/restaurants/1004")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\" : \"JOKER\", \"address\": \"Busan\"}"))
                .andExpect(status().isOk());
        verify(restaurantService).updateRestaurant(1004L,"JOKER", "Busan");
    }

}