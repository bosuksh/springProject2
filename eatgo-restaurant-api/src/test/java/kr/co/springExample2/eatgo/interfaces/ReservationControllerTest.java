package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.ReservationService;
import kr.co.springExample2.eatgo.domain.Region;
import kr.co.springExample2.eatgo.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void list() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJPd25lciIsInJlc3RhdXJhbnRJZCI6MzY5fQ.U9LA_OO3NrAEijPjqz3SJgwKotMrXohVI3ubx4g2mnc";

        Long restaurantId = 369L;
        Long userId = 1004L;
        String name = "John";
        String date = "2019-12-24";
        String time = "20:00";
        Integer partySize = 20;

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .restaurantId(restaurantId)
                .name(name)
                .date(date)
                .partySize(partySize)
                .time(time)
                .build();
      //  given(reservationService.addReservation(any(),any(),any(),any(),any(),any())).willReturn(mockReservation);

        mvc.perform(get("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token))
                .andExpect(status().isOk());

        verify(reservationService).getReservations(eq(restaurantId));
    }

}