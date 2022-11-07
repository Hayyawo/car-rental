package com.example.carrental.car;

import com.example.carrental.reservation.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "car")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private boolean isCarFreeNow;
    private int price;
    private int numberOfDays;
    private int horsePower;
    private double secToHundred;

    @OneToMany
    private List<Reservation> reservationList = new ArrayList<>();
}
