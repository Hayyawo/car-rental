package com.example.carrental.accessories;

import com.example.carrental.price.priceforaccessory.PriceForAccessory;
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
    @OneToOne
    private PriceForAccessory priceForAccessory;
    @ManyToOne
    private Reservation reservation;
}
