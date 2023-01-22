package com.example.carrental.reservation;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<Integer> accessoriesIds;
    private Long carId;
}
