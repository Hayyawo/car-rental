package com.example.carrental.accessories;

import com.example.carrental.price.priceforaccessory.PriceForAccessory;
import com.example.carrental.reservation.Reservation;
import lombok.*;

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
    @OneToOne(mappedBy = "accessory")
    private PriceForAccessory priceForAccessory;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
