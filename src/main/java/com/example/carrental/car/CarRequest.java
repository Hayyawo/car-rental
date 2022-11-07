package com.example.carrental.car;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarRequest {
    private Long id;
    private String name;
    private String model;
    private boolean isCarFreeNow;
    private int price;
    private int numberOfDays;
    private int horsePower;
    private double secToHundred;
}
