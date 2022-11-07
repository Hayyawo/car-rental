package com.example.carrental.reservation;

import lombok.*;

import java.time.LocalDate;
@Builder
@Data
public class ReservationResponse {
    private Long id;
    private boolean isCarFree;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long carId;
}
