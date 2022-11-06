package com.example.carrental.reservation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationRequest {
    private Long id;
    private boolean isCarFree;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int carId;
}
