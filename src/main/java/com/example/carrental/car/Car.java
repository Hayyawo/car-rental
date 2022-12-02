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
    private int priceForDay;
    private int horsePower;
    private double secToHundred;
    private boolean isofix;
    private int tankCapacity;
    @Enumerated(EnumType.STRING)
    private TypeOfFuel typeOfFuel;
    @OneToMany(mappedBy = "car", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Reservation> reservationList = new ArrayList<>();
}
