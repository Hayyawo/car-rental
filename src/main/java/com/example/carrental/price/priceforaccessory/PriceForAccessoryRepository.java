package com.example.carrental.price.priceforaccessory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceForAccessoryRepository extends JpaRepository<PriceForAccessory, Long> {
}