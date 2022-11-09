package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.CarDoesNotExists;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final CarRepository carRepository;

    public ReservationMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Reservation mapFromDto(ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getCarId())
                .orElseThrow(CarDoesNotExists::new);
        return Reservation.builder()
                .dateFrom(reservationRequest.getDateFrom())
                .dateTo(reservationRequest.getDateTo())
                .priceForReservation(reservationRequest.getPriceForReservation())
                .car(car)
                .build();
    }

    public ReservationResponse mapToDto(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .dateTo(reservation.getDateTo())
                .idsOfAccessories(reservation.getAccessories())
                .priceForReservation(reservation.getPriceForReservation())
                .dateFrom(reservation.getDateFrom())
                .carId(reservation.getCar().getId())
                .build();

    }
}
