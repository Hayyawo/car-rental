package com.example.carrental.car;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarResponse {
    private Long id;
    private String name;
    private String model;
    private int price;
    private int numberOfDays;
    private int horsePower;
    private double secToHundred;
}
