package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.car.exceptions.CarDoesNotExists;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final CarRepository carRepository;

    public ReservationMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public  Reservation mapFromDto(ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getId())
                .orElseThrow(CarDoesNotExists::new);
        return Reservation.builder()
                .dateFrom(reservationRequest.getDateFrom())
                .dateTo(reservationRequest.getDateTo())
                .isCarFree(reservationRequest.isCarFree())
                .car(car)
                .build();
    }
}
