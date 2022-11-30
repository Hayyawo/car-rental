package com.example.carrental.car;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class CarResponse {
    private Long id;
    private String name;
    private String model;
    private int horsePower;
    private double secToHundred;
    private List<Long> reservationIds;
}
