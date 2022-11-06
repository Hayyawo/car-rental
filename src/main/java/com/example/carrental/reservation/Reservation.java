package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCarFree;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @ManyToOne
    private Car car;
}
