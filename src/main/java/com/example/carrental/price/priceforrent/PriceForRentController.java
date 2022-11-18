package com.example.carrental.price.priceforrent;

import com.example.carrental.reservation.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PriceForRentController {
    private final PriceForRentService priceForRentService;

    public PriceForRentController(PriceForRentService priceForRentService) {
        this.priceForRentService = priceForRentService;
    }

    @PostMapping("/api/price-for-rent")
    public ResponseEntity<PriceForRentResponse> addPrice(@RequestBody PriceForRentRequest priceForRentRequest) {
        return new ResponseEntity<>(priceForRentService.save(priceForRentRequest), HttpStatus.OK);

    }

    @GetMapping("/api/price-for-rent/{priceId}")
    public ResponseEntity<PriceForRentResponse> getPrice(@PathVariable long priceId) {
        return new ResponseEntity<>(priceForRentService.findById(priceId), HttpStatus.OK);
    }

    @PutMapping("/api/price-for-rent/")
    public ResponseEntity<PriceForRentResponse> editPrice(@RequestBody PriceForRentRequest priceForRentRequest) {
        return new ResponseEntity<>(priceForRentService.editPrice(priceForRentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/api/price-for-rent/{priceId}")
    public void deletePrice(@PathVariable Long priceId) {
        priceForRentService.delete(priceId);
    }
}
