package com.example.carrental.accessories;

import com.example.carrental.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "accessory")
@Getter
@Setter
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean paidDaily;
    @OneToOne
    private Deposit deposit;
    @ManyToOne
    private Reservation reservation;
}
