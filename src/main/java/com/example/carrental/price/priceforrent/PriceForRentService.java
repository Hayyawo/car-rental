package com.example.carrental.price.priceforrent;

import com.example.carrental.exceptions.PriceDoesNotExists;
import com.example.carrental.exceptions.PriceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PriceForRentService {
    private final PriceForRentRepository priceForRentRepository;
    private final PriceForRentMapper priceForRentMapper;

    public PriceForRentService(PriceForRentRepository priceForRentRepository, PriceForRentMapper priceForRentMapper) {
        this.priceForRentRepository = priceForRentRepository;
        this.priceForRentMapper = priceForRentMapper;
    }

    public double calculatePriceForReservation(Long carId, int numberOfDays) {
        PriceForRent priceForRent = priceForRentRepository.findByCarId(carId)
                .orElseThrow();
        return priceForRent.getPriceForDay() * numberOfDays;
    }

    public PriceForRentResponse save(PriceForRentRequest priceForRentRequest) {
        PriceForRent price = priceForRentMapper.map(priceForRentRequest);
        priceForRentRepository.save(price);

        return priceForRentMapper.map(price);
    }

    public PriceForRentResponse findById(long priceId) {
        PriceForRent priceForRent = priceForRentRepository.findById(priceId)
                .orElseThrow(PriceNotFoundException::new);
        return priceForRentMapper.map(priceForRent);
    }

    public PriceForRentResponse editPrice(PriceForRentRequest priceForRentRequest) {
        PriceForRent priceForRent = priceForRentRepository.findByCarId(priceForRentRequest.getCarId())
                .orElseThrow(PriceDoesNotExists::new);
        priceForRent.setPriceForDay(priceForRentRequest.getPriceForDay());
        priceForRent.setCarId(priceForRentRequest.getCarId());
        priceForRentRepository.save(priceForRent);

        return priceForRentMapper.map(priceForRent);
    }

    public void delete(Long priceId) {
        PriceForRent price = priceForRentRepository.findById(priceId)
                .orElseThrow(PriceDoesNotExists::new);
        priceForRentRepository.delete(price);
    }
}
