package com.example.carrental.accessories;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessoryController {
    private final AccessoryService accessoryService;

    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/api/accessory")
    public boolean addAccessoryToReservation(@RequestBody AccessoryRequest accessoryRequest){
       return accessoryService.addAccessoryToReservation(accessoryRequest);
    }
}
