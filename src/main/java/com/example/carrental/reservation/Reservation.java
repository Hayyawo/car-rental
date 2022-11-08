package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private double priceForReservation;
    @ManyToOne
    private Car car;
}
