package com.example.carrental.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ReservationRequest {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<Integer> accessoriesIds;
    private Long carId;
}
