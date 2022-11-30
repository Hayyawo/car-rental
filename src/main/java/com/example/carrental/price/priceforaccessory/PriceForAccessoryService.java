package com.example.carrental.price.priceforaccessory;

import com.example.carrental.car.TypeOfFuel;
import com.example.carrental.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceForAccessoryService {
    public double countPriceForAccessory(Reservation reservation, PriceForAccessory priceForAccessory) {
        double accessoryPriceOverall = priceForAccessory.getPrice();
        if (priceForAccessory.isPaidDaily()) {
            int numberOfDays = reservation.getDateTo().compareTo(reservation.getDateFrom());
            accessoryPriceOverall = priceForAccessory.getPrice() * numberOfDays;
        } else if (priceForAccessory.getId() == 1) {
            if (reservation.getCar().getTypeOfFuel().equals(TypeOfFuel.GAS)){
                accessoryPriceOverall = 6.5 * reservation.getCar().getTankCapacity();
            } else {
                accessoryPriceOverall = 8.0 * reservation.getCar().getTankCapacity();
            }
        }
        return accessoryPriceOverall;
    }
}
