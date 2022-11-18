package com.example.carrental.accessories;

import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.price.priceforaccessory.PriceForAccessoryService;
import com.example.carrental.reservation.Reservation;
import com.example.carrental.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final ReservationRepository reservationRepository;
    private final PriceForAccessoryService priceForAccessoryService;

    public AccessoryService(AccessoryRepository accessoryRepository, ReservationRepository reservationRepository, PriceForAccessoryService priceForAccessoryService) {
        this.accessoryRepository = accessoryRepository;
        this.reservationRepository = reservationRepository;
        this.priceForAccessoryService = priceForAccessoryService;
    }

    public boolean addAccessoryToReservation(AccessoryRequest accessoryRequest) {
        Accessory accessory = accessoryRepository.findById(accessoryRequest.getAccessoryId())
                .orElseThrow(AccessoryDoesNotExists::new);
        Reservation reservation = reservationRepository.findById(accessoryRequest.getReservationId())
                .orElseThrow(ReservationDoesNotExists::new);

        reservation.getAccessories().add(accessory);

        double priceForAccessories = priceForAccessoryService.countPriceForAccessory(reservation, accessory.getPriceForAccessory());
        reservation.setTotalPrice(reservation.getTotalPrice() + priceForAccessories);
        reservationRepository.save(reservation);
        return true;
    }
}
