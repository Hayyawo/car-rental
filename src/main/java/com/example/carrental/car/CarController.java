package com.example.carrental.car;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/api/car")
    public ResponseEntity<CarRequest> addCar(@RequestBody CarRequest carRequest) {
        carService.save(carRequest);
        return new ResponseEntity<>(carRequest, HttpStatus.OK);
    }
    @GetMapping("/api/car/{carId}")
    public ResponseEntity<CarResponse> getSpecificCar(@PathVariable long carId){
        return new ResponseEntity<>(carService.findSingleCar(carId), HttpStatus.OK);
    }

    @GetMapping("/api/cars")
    public List<CarResponse> allCars() {
        return carService.findAll();
    }

    @PutMapping("/api/car")
    public ResponseEntity<CarRequest> editCar(@RequestBody CarRequest carRequest) {
        carService.editCar(carRequest);
        return new ResponseEntity<>(carRequest, HttpStatus.OK);
    }

    @DeleteMapping("/api/car/{id}")
    public boolean deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return true;
    }
    @GetMapping("/api/available-cars")
    public List<CarResponse> allAvailableCars(){
        return carService.findAllAvailableCars();
    }
}
