package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.RestaurantService;
import kr.co.springExample2.eatgo.domain.MenuItem;
import kr.co.springExample2.eatgo.domain.Restaurant;
import kr.co.springExample2.eatgo.domain.RestaurantNotFoundException;
import kr.co.springExample2.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void detailWithExisted() throws Exception {

        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder().name("Kimchi").build();

        Review review = Review.builder().name("JOKER").score(5).description("Great!").build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));
        restaurant1.setReviews(Arrays.asList(review));
        given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant1);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"") ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(containsString("Great!")));

    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurantById(404L)).willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValidData() throws Exception {
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
    public void createWithInvalidData() throws Exception {

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"\", \"address\":\"\"}"))
                .andExpect(status().isBadRequest());
               // .andExpect(content().string(""));

    }

    @Test
    public void updateWithValidData() throws Exception {

        mvc.perform(put("/restaurants/1004")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\" : \"JOKER\", \"address\": \"Busan\"}"))
                .andExpect(status().isOk());
        verify(restaurantService).updateRestaurant(1004L,"JOKER", "Busan");
    }

    @Test
    public void updateWithInvalidData() throws Exception {

        mvc.perform(put("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"\", \"address\": \"\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateWithoutName() throws Exception {

        mvc.perform(put("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"\", \"address\": \"Seoul\"}"))
                .andExpect(status().isBadRequest());

    }

}