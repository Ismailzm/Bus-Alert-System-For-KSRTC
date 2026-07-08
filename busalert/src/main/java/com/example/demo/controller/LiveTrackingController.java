package com.example.demo.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LiveTrackingResponse;
import com.example.demo.service.LiveTrackingService;

@RestController
@RequestMapping("/api/live")
@CrossOrigin(origins = "*")
public class LiveTrackingController {

    private final LiveTrackingService liveTrackingService;

    public LiveTrackingController(LiveTrackingService liveTrackingService) {
        this.liveTrackingService = liveTrackingService;
    }

    @GetMapping("/{busNumber}")
    public LiveTrackingResponse trackBus(
            @PathVariable String busNumber) {

        return liveTrackingService.trackBus(busNumber);

    }

}
