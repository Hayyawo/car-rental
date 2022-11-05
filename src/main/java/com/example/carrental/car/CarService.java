package com.example.carrental.car;

import com.example.carrental.car.exceptions.CarDoesNotExists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public boolean save(CarRequest carRequest) {
        Car car = CarMapper.mapFromDto(carRequest);
        carRepository.save(car);
        return true;
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
        car.setPrice(carRequest.getPrice());
        car.setNumberOfDays(carRequest.getNumberOfDays());
        car.setSecToHundred(carRequest.getSecToHundred());
        carRepository.save(car);
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(CarDoesNotExists::new);
        carRepository.delete(car);
    }
}
