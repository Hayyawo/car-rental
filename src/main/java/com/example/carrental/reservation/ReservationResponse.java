package com.example.carrental.reservation;

import com.example.carrental.accessories.Accessory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class ReservationResponse {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double priceForReservation;
    private Long carId;
    private List<Accessory> idsOfAccessories;
}
