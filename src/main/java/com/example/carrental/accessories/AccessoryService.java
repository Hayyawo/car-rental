package com.example.carrental.accessories;

import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.price.priceforaccessory.PriceForAccessoryService;
import com.example.carrental.reservation.Reservation;
import com.example.carrental.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final ReservationRepository reservationRepository;
    private final PriceForAccessoryService priceForAccessoryService;

    public void addAccessoryToReservation(AccessoryRequest accessoryRequest) {
        Accessory accessory = findAccessory(accessoryRequest);
        Reservation reservation = findReservation(accessoryRequest);
        addAccessoryToReservation(accessory, reservation);
        addPriceForReservation(accessory, reservation);

        reservationRepository.save(reservation);
    }

    private void addPriceForReservation(Accessory accessory, Reservation reservation) {
        double priceForAccessories = priceForAccessoryService.countPriceForAccessory(reservation, accessory.getPriceForAccessory());
        reservation.setTotalPrice(reservation.getTotalPrice() + priceForAccessories);
    }

    private static void addAccessoryToReservation(Accessory accessory, Reservation reservation) {
        List<Accessory> accessories = reservation.getAccessories();
        if (!accessories.isEmpty()) {
            for (Accessory accessory1 : accessories) {
                if (accessory1.getId().equals(accessory.getId())) {
                    throw new IllegalArgumentException("Accessory already added");
                }
            }
        } else {
            accessories.add(accessory);
            reservation.setAccessories(accessories);
            accessory.setReservation(reservation);
        }
    }

    private Accessory findAccessory(AccessoryRequest accessoryRequest) {
        return accessoryRepository.findById(accessoryRequest.getAccessoryId())
                .orElseThrow(AccessoryDoesNotExists::new);
    }

    private Reservation findReservation(AccessoryRequest accessoryRequest) {
        return reservationRepository.findById(accessoryRequest.getReservationId())
                .orElseThrow(ReservationDoesNotExists::new);
    }
}
