package com.example.carrental.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Car does not exists")
public class CarDoesNotExists extends RuntimeException {
}