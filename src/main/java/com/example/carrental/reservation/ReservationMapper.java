package com.example.carrental.reservation;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.accessories.AccessoryRepository;
import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.CarDoesNotExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final CarRepository carRepository;
    private final AccessoryRepository accessoryRepository;


    public Reservation mapFromDto(ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getCarId())
                .orElseThrow(CarDoesNotExists::new);
        return Reservation.builder()
                .dateFrom(reservationRequest.getDateFrom())
                .dateTo(reservationRequest.getDateTo())
                .car(car)
                .build();
    }

    public ReservationResponse mapToDto(Reservation reservation) {
        List<Long> accessoriesIds = new ArrayList<>();
        if (reservation.getAccessories() != null) {
            accessoriesIds = reservation.getAccessories().stream().map(Accessory::getId).toList();
        }
        return ReservationResponse.builder()
                .id(reservation.getId())
                .dateTo(reservation.getDateTo())
                .totalPrice(reservation.getTotalPrice())
                .dateFrom(reservation.getDateFrom())
                .carId(reservation.getCar().getId())
                //todo cos wywala blad jak chce wrzucic ta liste ale typu Accessory
                .accessoryIds(accessoriesIds)
                .build();

    }
}
