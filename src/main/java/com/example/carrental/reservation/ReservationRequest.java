package com.example.carrental.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReservationRequest {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long carId;
}
