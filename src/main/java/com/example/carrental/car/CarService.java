package com.example.carrental.car;

import com.example.carrental.exceptions.CarDoesNotExists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarResponse save(CarRequest carRequest) {
        Car car = carMapper.mapFromDto(carRequest);
        carRepository.save(car);
        return carMapper.mapToDto(car);
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
                .map(carMapper::mapToDto)
                .orElseThrow(CarDoesNotExists::new);
    }

    private static List<Car> getAvailableCars(List<Car> cars) {
        return cars.stream()
                .filter(car -> CollectionUtils.isEmpty(car.getReservationList()))
                .toList();
    }
    public Page<CarResponse> findByFilter(CarFilter carFilter, Pageable pageable) {
        return carRepository.findByFiler(carFilter, pageable)
                .map(carMapper::mapToDto);
    }
}
