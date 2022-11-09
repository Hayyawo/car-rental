package com.example.carrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Accessory does not exists")
public class AccessoryDoesNotExists extends RuntimeException {
}