package com.example.carrental.accessories;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;

    @PostMapping("/api/accessory")
    public void addAccessoryToReservation(@RequestBody AccessoryRequest accessoryRequest){
       accessoryService.addAccessoryToReservation(accessoryRequest);
    }
}
