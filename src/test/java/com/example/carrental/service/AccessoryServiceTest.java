package com.example.carrental.service;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.accessories.AccessoryRepository;
import com.example.carrental.accessories.AccessoryRequest;
import com.example.carrental.accessories.AccessoryService;
import com.example.carrental.car.Car;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.exceptions.ReservationDoesNotExists;
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
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTest {
    @Mock
    private AccessoryRepository accessoryRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private AccessoryService accessoryService;

    @Test
    void addAccessoryToReservation_correct() {
        //given
        Accessory accessory = new Accessory();
        Reservation reservation = new Reservation();
        accessory.setId(1L);
        reservation.setCar(new Car());
        AccessoryRequest accessoryRequest = new AccessoryRequest();
        when(accessoryRepository.findById(accessoryRequest.getAccessoryId())).thenReturn(Optional.of(accessory));
        when(reservationRepository.findById(accessoryRequest.getReservationId())).thenReturn(Optional.of(reservation));
        //when
        accessoryService.addAccessoryToReservation(accessoryRequest);
        //then
        verify(reservationRepository).save(reservation);
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

//    @Test
//    void addAccessoryToReservation() {
//        //given
//        Accessory accessory = new Accessory();
//        Reservation reservation = new Reservation();
//        reservation.setDateFrom(LocalDate.of(2022,11,1));
//        reservation.setDateTo(LocalDate.of(2022,11,11));
//        accessory.setPaidDaily(true);
//        accessory.setId(1L);
//        reservation.setCar(new Car());
//        AccessoryRequest accessoryRequest = new AccessoryRequest();
//        when(accessoryRepository.findById(accessoryRequest.getAccessoryId())).thenReturn(Optional.of(accessory));
//        when(reservationRepository.findById(accessoryRequest.getReservationId())).thenReturn(Optional.of(reservation));
//        //when
//        accessoryService.addAccessoryToReservation(accessoryRequest);
//        //then
//        verify(reservationRepository).save(reservation);
//    }
}
