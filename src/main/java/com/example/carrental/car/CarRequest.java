package com.example.carrental.car;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private Long id;
    private String name;
    private String model;
    private int horsePower;
    private double secToHundred;
}
