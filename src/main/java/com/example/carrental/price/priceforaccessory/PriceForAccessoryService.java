package com.example.carrental.price.priceforaccessory;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.reservation.Reservation;
import org.springframework.stereotype.Service;

@Service
public class PriceForAccessoryService {
    private final PriceForAccessoryRepository priceForAccessoryRepository;

    public PriceForAccessoryService(PriceForAccessoryRepository priceForAccessoryRepository) {
        this.priceForAccessoryRepository = priceForAccessoryRepository;
    }

    public double countPriceForAccessory(Reservation reservation, PriceForAccessory priceForAccessory) {
        double accessoryPriceOverall = priceForAccessory.getPrice();
        if (priceForAccessory.isPaidDaily()) {
            int numberOfDays = reservation.getDateTo().compareTo(reservation.getDateFrom());
            accessoryPriceOverall = priceForAccessory.getPrice() * numberOfDays;
        } else if (priceForAccessory.getId() == 1) {
            if (reservation.getCar().isPetrol()) {
                accessoryPriceOverall = 6.5 * reservation.getCar().getTankCapacity();
            } else {
                accessoryPriceOverall = 8.0 * reservation.getCar().getTankCapacity();
            }
        }
        return accessoryPriceOverall;
    }
}
