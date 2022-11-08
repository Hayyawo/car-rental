package com.example.carrental.reservation;

import lombok.*;

import java.time.LocalDate;
@Builder
@Data
public class ReservationResponse {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double priceForReservation;
    private Long carId;
}
