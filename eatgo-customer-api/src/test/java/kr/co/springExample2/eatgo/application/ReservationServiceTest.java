package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Reservation;
import kr.co.springExample2.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {


    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }
    @Test
    public void addReservations() {
        Long restaurantId = 369L;
        Long userId = 1004L;
        String name = "John";
        String date = "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;


        given(reservationRepository.save(any())).will(
            invocation -> {
                Reservation reservation = invocation.getArgument(0);
                return reservation;
            }

            );

        Reservation reservation = reservationService.addReservation(restaurantId,userId,name,date,time,partySize);

        assertThat(reservation.getName(), is(name));
        verify(reservationRepository).save(any());
    }

}