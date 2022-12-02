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

    @GetMapping("/api/reservations")
    public List<ReservationResponse> reservationList(Long carId){
        return reservationService.reservationList(carId);
    }

    @DeleteMapping("/api/reservation")
    public void deleteOldReservations(){
        reservationService.deleteReservationIfTimePassed();
    }
    @DeleteMapping("/api/reservation/{reservationId}")
    public void deleteReservation(@PathVariable long reservationId){
        reservationService.delete(reservationId);
    }

    @PutMapping("/api/reservations")
    public ResponseEntity<ReservationResponse> editReservation(ReservationRequest reservationRequest){
       return new ResponseEntity<>(reservationService.editReservation(reservationRequest),HttpStatus.OK);
    }
}
