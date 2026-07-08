package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BusTrip;
import com.example.demo.service.TripService;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "*")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // =============================
    // Start Trip
    // =============================

    @PostMapping("/start/{busNumber}")
    public BusTrip startTrip(@PathVariable String busNumber) {

        return tripService.startTrip(busNumber);

    }

    // =============================
    // End Trip
    // =============================

    @PostMapping("/end/{busNumber}")
    public String endTrip(@PathVariable String busNumber) {

        tripService.endTrip(busNumber);

        return "Trip Ended Successfully";

    }
    
 // =============================
 // Reverse Trip
 // =============================

 @PostMapping("/reverse/{busNumber}")
 public BusTrip reverseTrip(@PathVariable String busNumber){

     return tripService.reverseTrip(busNumber);

 }

}