package com.example.carrental.car;

import com.example.carrental.reservation.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarMapper {
    public Car mapFromDto(CarRequest carRequest) {
        return Car.builder()
                .name(carRequest.getName())
                .model(carRequest.getModel())
                .horsePower(carRequest.getHorsePower())
                .secToHundred(carRequest.getSecToHundred())
                .build();
    }

    public CarResponse mapToDto(Car car) {
        List<Long> reservationIds = car.getReservationList().stream().map(Reservation::getId).toList();
        return CarResponse.builder()
                .id(car.getId())
                .reservationIds(reservationIds)
                .horsePower(car.getHorsePower())
                .model(car.getModel())
                .secToHundred(car.getSecToHundred())
                .name(car.getName())
                .build();
    }
}
