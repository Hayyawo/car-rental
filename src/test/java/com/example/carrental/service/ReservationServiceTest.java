package com.example.carrental.service;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.accessories.AccessoryRepository;
import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.reservation.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationMapper reservationMapper;
    @Mock
    private CarRepository carRepository;
    @Mock
    private AccessoryRepository accessoryRepository;
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void save_correct() {
        //given
        Reservation reservation = new Reservation();
        //when
        reservationRepository.save(reservation);
        //then
        verify(reservationRepository).save(reservation);
    }

    @Test
    void reservationList_correct_carIdEqualsNull() {
        //given
        Long carId = null;
        ArrayList<Reservation> reservationResponses = new ArrayList<>();
        when(reservationRepository.findAll()).thenReturn(reservationResponses);
        //when
        reservationService.reservationList(carId);
        //then
        verify(reservationRepository).findAll();

    }

    @Test
    void reservationList_correct_carIdOtherValue() {
        //given
        Long carId = 1L;
        ArrayList<Reservation> reservation = new ArrayList<>();
        when(reservationRepository.findByCar_Id(carId)).thenReturn(reservation);
        //when
        reservationService.reservationList(carId);
        //then
        verify(reservationRepository).findByCar_Id(carId);

    }

//    @Test
//    void deleteReservationIfTimePassed_correct() {
//        //given
//        List<Reservation> reservations = List.of(new Reservation());
//        when(reservationRepository.findAll()).thenReturn(reservations);
//        List<Reservation> reservations1 = reservations.stream()
//                .filter(reservation -> reservation.getDateTo().isBefore(LocalDate.now()))
//                .toList();
//        //when
//        reservationService.deleteReservationIfTimePassed();
//        //then
//        verify(reservationRepository).deleteAll(reservations1);
//    }
    @Test
    void editReservation_correct(){
        //given
        ReservationRequest reservationRequest = new ReservationRequest();
        Reservation reservation = new Reservation();
        Car car = new Car();
        when(reservationRepository.findById(reservationRequest.getId())).thenReturn(Optional.of(reservation));
        when(carRepository.findById(reservationRequest.getCarId())).thenReturn(Optional.of(car));
        //when
        reservationService.editReservation(reservationRequest);
        //then
        verify(reservationRepository).save(reservation);
    }

    @Test
    void delete_correct(){
        //given
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        Accessory accessory = new Accessory();
        List<Accessory> accessories = reservation.getAccessories();
        accessories.add(accessory);
        reservation.setAccessories(accessories);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        //when
        reservationService.delete(reservationId);
        //then
        verify(reservationRepository).delete(reservation);
    }
}
