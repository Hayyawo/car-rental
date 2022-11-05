package com.example.carrental.car;

public class CarMapper {
    public static Car mapFromDto(CarRequest carRequest) {
        return Car.builder()
                .name(carRequest.getName())
                .model(carRequest.getModel())
                .price(carRequest.getPrice())
                .numberOfDays(carRequest.getNumberOfDays())
                .horsePower(carRequest.getHorsePower())
                .secToHundred(carRequest.getSecToHundred())
                .build();
    }
    public static CarResponse mapToDto(Car car){
        return CarResponse.builder()
                .id(car.getId())
                .horsePower(car.getHorsePower())
                .model(car.getModel())
                .name(car.getName())
                .numberOfDays(car.getNumberOfDays())
                .price(car.getPrice())
                .build();
    }
}
