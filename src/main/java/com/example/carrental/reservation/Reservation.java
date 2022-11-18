package com.example.carrental.reservation;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.car.Car;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToMany
    private List<Accessory> accessories = new ArrayList<>();
}
