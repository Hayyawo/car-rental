package com.example.carrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Given price does not exists")
public class PriceDoesNotExists extends RuntimeException {
}