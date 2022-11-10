package com.example.carrental.car;

import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public  Car mapFromDto(CarRequest carRequest) {
        return Car.builder()
                .name(carRequest.getName())
                .model(carRequest.getModel())
                .priceForDay(carRequest.getPrice())
                .isCarFreeNow(carRequest.isCarFreeNow())
                .horsePower(carRequest.getHorsePower())
                .secToHundred(carRequest.getSecToHundred())
                .build();
    }
    public static CarResponse mapToDto(Car car){
        return CarResponse.builder()
                .id(car.getId())
                .isCarFreeNow(car.isCarFreeNow())
                .horsePower(car.getHorsePower())
                .model(car.getModel())
                .name(car.getName())
                .price(car.getPriceForDay())
                .build();
    }
}
