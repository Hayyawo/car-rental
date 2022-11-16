package com.example.carrental.reservation;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("/api/reservation")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(reservationService.save(reservationRequest), HttpStatus.OK);
    }

    @GetMapping("/api/reservations/{carId}")
    public List<ReservationResponse> reservationList(@PathVariable long carId){
        return reservationService.reservationList(carId);
    }

    @DeleteMapping("/api/reservation")
    public boolean deleteOldReservations(){
        return reservationService.deleteReservationIfTimePassed();
    }

    @PutMapping("/api/reservations")
    public ResponseEntity<ReservationResponse> editReservation(ReservationRequest reservationRequest){
       return new ResponseEntity<>(reservationService.editReservation(reservationRequest),HttpStatus.OK);
    }
}
