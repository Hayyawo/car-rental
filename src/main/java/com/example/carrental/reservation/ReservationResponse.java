package com.example.carrental.reservation;

import com.example.carrental.accessories.Accessory;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double totalPrice;
    private Long carId;
    private List<Long> accessoryList;
}
