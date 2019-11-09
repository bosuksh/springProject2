package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.RegionRepository;
import kr.co.springExample2.eatgo.domain.Reservation;
import kr.co.springExample2.eatgo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
        Reservation reservation =  Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservationRepository.save(reservation);
    }
}
