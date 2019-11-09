package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Reservation;
import kr.co.springExample2.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {

    private ReservationService restaurantService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        restaurantService = new ReservationService(reservationRepository);
    }

    @Test
    public void getReservations() {
        Long restaurantId = 369L;

        List<Reservation> reservations = restaurantService.getReservations(restaurantId);

        verify(reservationRepository).findAllByRestaurantId(restaurantId);
    }

}