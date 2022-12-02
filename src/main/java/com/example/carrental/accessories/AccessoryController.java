package com.example.carrental.accessories;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;
//todo nie dodaje sie
    @PostMapping("/api/accessory")
    public void addAccessoryToReservation(@RequestBody AccessoryRequest accessoryRequest){
       accessoryService.addAccessoryToReservation(accessoryRequest);
    }
}
