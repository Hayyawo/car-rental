package com.example.carrental.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
