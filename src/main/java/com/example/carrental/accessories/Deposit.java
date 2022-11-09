package com.example.carrental.accessories;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Deposit {
    @Id
    private Long id;
    private boolean depositPaid;
    @OneToOne
    private Accessory accessory;
}
