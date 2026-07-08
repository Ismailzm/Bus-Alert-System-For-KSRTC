package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TrackedBus;
import com.example.demo.service.TrackedBusService;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "*")
public class TrackedBusController {

    private final TrackedBusService trackedBusService;

    public TrackedBusController(
            TrackedBusService trackedBusService) {

        this.trackedBusService = trackedBusService;

    }

    // ==========================================
    // Start Tracking
    // ==========================================

    @PostMapping("/start")
    public TrackedBus startTracking(

            @RequestParam Long userId,

            @RequestParam String busNumber){

        return trackedBusService.startTracking(
                userId,
                busNumber);

    }

    // ==========================================
    // Stop Tracking
    // ==========================================

    @DeleteMapping("/stop")
    public void stopTracking(

            @RequestParam Long userId,

            @RequestParam String busNumber){

        trackedBusService.stopTracking(
                userId,
                busNumber);

    }

    // ==========================================
    // Users Tracking This Bus
    // ==========================================

    @GetMapping("/{busNumber}")
    public List<TrackedBus> getTrackedUsers(

            @PathVariable String busNumber){

        return trackedBusService.getTrackedUsers(
                busNumber);

    }

}