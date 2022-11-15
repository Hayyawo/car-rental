package com.example.carrental.price;

import org.springframework.stereotype.Service;

@Service
public class PriceService {
    private final PriceRepository priceRepository;


    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public double calculatePriceForReservation(Long carId, int numberOfDays) {
        Price price = priceRepository.findByCarId(carId)
                .orElseThrow();
        return  price.getPriceForDay() * numberOfDays;
    }
}
