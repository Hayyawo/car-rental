package com.example.carrental.reservation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int priceForReservation;
    private Long carId;
}
