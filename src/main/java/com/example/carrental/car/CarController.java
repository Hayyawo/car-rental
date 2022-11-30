package com.example.carrental.car;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/api/car")
    public ResponseEntity<CarResponse> addCar(@RequestBody CarRequest carRequest) {
        return new ResponseEntity<>( carService.save(carRequest), HttpStatus.OK);
    }
    @GetMapping("/api/car/{carId}")
    public ResponseEntity<CarResponse> getSpecificCar(@PathVariable long carId){
        return new ResponseEntity<>(carService.findSingleCar(carId), HttpStatus.OK);
    }
    @GetMapping("/api/available-cars")
    public List<CarResponse> allAvailableCars(){
        return carService.findAllAvailableCars();
    }
    @GetMapping("/api/cars")
    public Page<CarResponse> allCars(CarFilter carFilter, Pageable pageable) {
        return carService.findByFilter(carFilter, pageable);
    }
    @PutMapping("/api/car")
    public void editCar(@RequestBody CarRequest carRequest) {
        carService.editCar(carRequest);
    }

    @DeleteMapping("/api/car/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.delete(id);
    }

}
