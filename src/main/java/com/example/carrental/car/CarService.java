package com.example.carrental.car;

import com.example.carrental.exceptions.CarDoesNotExists;
import org.springframework.stereotype.Service;

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


    public void save(CarRequest carRequest) {
        Car car = carMapper.mapFromDto(carRequest);
        car.setCarFreeNow(true);
        carRepository.save(car);
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll().stream()
                .map(CarMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void editCar(CarRequest carRequest) {
        Car car = carRepository.findById(carRequest.getId())
                .orElseThrow(CarDoesNotExists::new);
        car.setName(carRequest.getName());
        car.setModel(car.getModel());
        car.setHorsePower(carRequest.getHorsePower());
        car.setPriceForDay(carRequest.getPrice());
        car.setSecToHundred(carRequest.getSecToHundred());
        carRepository.save(car);
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(CarDoesNotExists::new);
        carRepository.delete(car);
    }

    public List<CarResponse> findAllAvailableCars() {
        return carRepository.findAll()
                .stream()
                .filter(Car::isCarFreeNow)
                .map(CarMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
