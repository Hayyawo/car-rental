package com.example.carrental.accessories;

import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.reservation.Reservation;
import com.example.carrental.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final ReservationRepository reservationRepository;

    public AccessoryService(AccessoryRepository accessoryRepository, ReservationRepository reservationRepository) {
        this.accessoryRepository = accessoryRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean addAccessoryToReservation(AccessoryRequest accessoryRequest) {
        Accessory accessory = accessoryRepository.findById(accessoryRequest.getAccessoryId())
                .orElseThrow(AccessoryDoesNotExists::new);
        Reservation reservation = reservationRepository.findById(accessoryRequest.getReservationId())
                .orElseThrow(ReservationDoesNotExists::new);

        reservation.getAccessories().add(accessory);
        double priceForAccessories = countPriceForAccessories(reservation, accessory);
        reservation.setTotalPrice(reservation.getTotalPrice() + priceForAccessories);
        reservationRepository.save(reservation);
        return true;
    }

    private double countPriceForAccessories(Reservation reservation, Accessory accessory) {
        double accessoryPriceOverall = accessory.getPrice();
        if (accessory.isPaidDaily()) {
            int numberOfDays = reservation.getDateTo().compareTo(reservation.getDateFrom());
            accessoryPriceOverall = accessory.getPrice() * numberOfDays;
        } else if (accessory.getId() == 1) {
            if(reservation.getCar().isPetrol()) {
                accessoryPriceOverall = 6.5 * reservation.getCar().getTankCapacity();
            }else {
                accessoryPriceOverall = 8.0 * reservation.getCar().getTankCapacity();
            }
        }
        return accessoryPriceOverall;
    }
}
