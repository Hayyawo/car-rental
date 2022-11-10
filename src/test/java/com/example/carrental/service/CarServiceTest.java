package com.example.carrental.service;

import com.example.carrental.car.*;
import com.example.carrental.exceptions.CarDoesNotExists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarService carService;

    @Test
    void save_correct() {
        //given
        Car car = new Car();
        CarRequest carRequest = new CarRequest();
        when(carMapper.mapFromDto(carRequest)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(any());
        //when
        carService.save(carRequest);
        //then
        verify(carRepository).save(car);
    }

    @Test
    void findAll_correct() {
        //given
        List<Car> cars = List.of(new Car());
        when(carRepository.findAll()).thenReturn(cars);
        //when
        carService.findAll();
        //then
        verify(carRepository).findAll();
    }

    @Test
    void editCar_correct() {
        //given
        CarRequest carRequest = new CarRequest();
        Car car = new Car();
        when(carRepository.findById(carRequest.getId())).thenReturn(Optional.of(car));
        //when
        carService.editCar(carRequest);
        //then
        verify(carRepository).save(car);
    }

    @Test
    void editCar_throw_CarDoesNotExists() {
        //given
        CarRequest carRequest = new CarRequest();
        when(carRepository.findById(carRequest.getId())).thenThrow(CarDoesNotExists.class);
        //when

        //then
        Assertions.assertThrows(CarDoesNotExists.class, () -> carService.editCar(carRequest));
    }

    @Test
    void delete_throw_CarDoesNotExists() {
        //given
        Car car = new Car();
        when(carRepository.findById(car.getId())).thenThrow(CarDoesNotExists.class);
        //when
        //then
        Assertions.assertThrows(CarDoesNotExists.class, () -> carService.delete(car.getId()));

    }

    @Test
    void delete_correct() {
        //given
        Car car = new Car();
        car.setId(1L);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        //when
        carService.delete(car.getId());
        //then
        verify(carRepository).delete(car);
    }

    @Test
    void findAllAvailableCars_correct() {
        //given
        Car car = new Car();
        List<Car> cars = List.of(car);
        when(carRepository.findAll()).thenReturn(cars);
        //when
        carService.findAllAvailableCars();
        //then
        verify(carRepository).findAll();
    }

}
