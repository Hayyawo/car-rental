package com.example.carrental.price.priceforrent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceForRentRepository extends JpaRepository<PriceForRent, Long> {
    Optional<PriceForRent> findByCarId(Long carId);
}
