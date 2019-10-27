package kr.co.springExample2.eatgo.domain;

public class RestaurantNotFoundException  extends RuntimeException{

    public  RestaurantNotFoundException(Long id) {
        super("Could not find restaurant " + id);
    }
}
