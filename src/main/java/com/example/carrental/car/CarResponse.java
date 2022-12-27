package com.example.carrental.car;

import lombok.*;

import java.util.List;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Long id;
    private String name;
    private String model;
    private int horsePower;
    private double secToHundred;
    private List<Long> reservationIds;
}
