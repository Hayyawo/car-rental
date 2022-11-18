package com.example.carrental.price.priceforaccessory;

import com.example.carrental.accessories.Accessory;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PriceForAccessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean paidDaily;
    private double price;
    @OneToOne
    private Accessory accessory;
}
