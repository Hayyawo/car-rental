package com.example.carrental.reservation;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("/api/reservation")
    public ResponseEntity<ReservationRequest> addReservation(@RequestBody ReservationRequest reservationRequest){
        reservationService.save(reservationRequest);
        return new ResponseEntity<>(reservationRequest, HttpStatus.OK);
    }
}
