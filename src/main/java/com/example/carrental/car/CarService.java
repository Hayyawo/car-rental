package com.example.carrental.car;

import com.example.carrental.exceptions.CarDoesNotExists;
import com.example.carrental.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    public CarResponse save(CarRequest carRequest) {
        Car car = carMapper.mapFromDto(carRequest);
        carRepository.save(car);
        return carMapper.mapToDto(car);
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll().stream()
                .map(carMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void editCar(CarRequest carRequest) {
        Car car = carRepository.findById(carRequest.getId())
                .orElseThrow(CarDoesNotExists::new);
        car.setName(carRequest.getName());
        car.setModel(car.getModel());
        car.setHorsePower(carRequest.getHorsePower());
        car.setSecToHundred(carRequest.getSecToHundred());
        carRepository.save(car);
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(CarDoesNotExists::new);
        carRepository.delete(car);
    }


    public List<CarResponse> findAllAvailableCars() {
        List<Car> cars = carRepository.findAll();

        return getAvailableCars(cars).stream()
                .map(carMapper::mapToDto)
                .collect(Collectors.toList());
    }


    public CarResponse findSingleCar(long carId) {
        return carRepository.findById(carId)
                .stream()
                .map(carMapper::mapToDto)
                .findAny()
                .orElseThrow(CarDoesNotExists::new);
    }

    private static List<Car> getAvailableCars(List<Car> cars) {
        List<Car> avaiableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getReservationList().isEmpty()) {
                avaiableCars.add(car);
            }
        }
        return avaiableCars;
    }
}
