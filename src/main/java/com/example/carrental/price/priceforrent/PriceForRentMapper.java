package com.example.carrental.price.priceforrent;

import org.springframework.stereotype.Component;

@Component
public class PriceForRentMapper {
    public PriceForRent map(PriceForRentRequest priceForRentRequest) {
        return PriceForRent.builder()
                .priceForDay(priceForRentRequest.getPriceForDay())
                .carId(priceForRentRequest.getCarId())
                .build();
    }

    public PriceForRentResponse map(PriceForRent priceForRent) {
        return PriceForRentResponse.builder()
                .id(priceForRent.getId())
                .priceForDay(priceForRent.getPriceForDay())
                .carId(priceForRent.getCarId())
                .build();
    }
}
