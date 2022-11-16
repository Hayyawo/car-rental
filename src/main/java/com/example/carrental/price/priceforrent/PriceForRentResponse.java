package com.example.carrental.price.priceforrent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceForRentResponse {
    private Long id;
    private Long carId;
    private double priceForDay;
}
