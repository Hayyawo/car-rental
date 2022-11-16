package com.example.carrental.price.priceforrent;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceForRentRequest {
    private long carId;
    private double priceForDay;
}
