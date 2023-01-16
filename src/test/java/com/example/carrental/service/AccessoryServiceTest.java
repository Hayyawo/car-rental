package com.example.carrental.service;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.accessories.AccessoryRepository;
import com.example.carrental.accessories.AccessoryRequest;
import com.example.carrental.accessories.AccessoryService;
import com.example.carrental.car.Car;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.price.priceforaccessory.PriceForAccessoryService;
import com.example.carrental.reservation.Reservation;
import com.example.carrental.reservation.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTest {
    @Mock
    private AccessoryRepository accessoryRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private PriceForAccessoryService priceForAccessoryService;
    @InjectMocks
    private AccessoryService accessoryService;

    @Test
    void addAccessoryToReservation_correct(){
        //given
        Accessory accessory = new Accessory();
        Reservation reservation = new Reservation();

        reservation.setId(1L);
        AccessoryRequest accessoryRequest = new AccessoryRequest();

        accessory.setId(0L);
        accessory.setReservation(reservation);
        when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.of(accessory));
        when(reservationRepository.findById(accessoryRequest.getReservationId())).thenReturn(Optional.of(reservation));
        //when
        accessoryService.addAccessoryToReservation(accessoryRequest);
        //then
        verify(reservationRepository).save(reservation);
    }
    @Test
    void addAccessoryToReservation_throw_IllegalArgumentException(){
        //given
        Accessory accessory = new Accessory();
        Reservation reservation = new Reservation();

        reservation.setId(1L);
        AccessoryRequest accessoryRequest = new AccessoryRequest();
        List<Accessory> accessories = reservation.getAccessories();
        accessories.add(accessory);
        accessories.add(accessory);
        accessory.setId(0L);
        accessory.setReservation(reservation);
        when(accessoryRepository.findById(accessory.getId())).thenReturn(Optional.of(accessory));
        when(reservationRepository.findById(accessoryRequest.getReservationId())).thenReturn(Optional.of(reservation));
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> accessoryService.addAccessoryToReservation(accessoryRequest));

    }
    @Test
    void addAccessoryToReservation_throw_AccessoryDoesNotExists() {
        //given
        Accessory accessory = new Accessory();
        Reservation reservation = new Reservation();
        accessory.setId(1L);
        reservation.setCar(new Car());
        AccessoryRequest accessoryRequest = new AccessoryRequest();
        when(accessoryRepository.findById(accessoryRequest.getAccessoryId())).thenThrow(AccessoryDoesNotExists.class);
        //when
        //then
        Assertions.assertThrows(AccessoryDoesNotExists.class, () -> accessoryService.addAccessoryToReservation(accessoryRequest));
    }

    @Test
    void addAccessoryToReservation_throw_ReservationDoesNotExists() {
        //given
        Accessory accessory = new Accessory();
        Reservation reservation = new Reservation();
        accessory.setId(1L);
        reservation.setCar(new Car());
        AccessoryRequest accessoryRequest = new AccessoryRequest();
        when(accessoryRepository.findById(accessoryRequest.getAccessoryId())).thenReturn(Optional.of(accessory));
        when(reservationRepository.findById(accessoryRequest.getReservationId())).thenThrow(ReservationDoesNotExists.class);
        //when
        //then
        Assertions.assertThrows(ReservationDoesNotExists.class, () -> accessoryService.addAccessoryToReservation(accessoryRequest));
    }


}
