package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Alert;
import com.example.demo.entity.Bus;
import com.example.demo.service.AlertService;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // Create Alert
    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    // Get Alerts of a User
    @GetMapping("/user/{userId}")
    public List<Alert> getUserAlerts(@PathVariable Long userId) {
        return alertService.getAlertsByUser(userId);
    }
    
    @GetMapping("/{alertId}/matches")
    public List<Bus> getMatchingBuses(@PathVariable Long alertId) {

        return alertService.findMatchingBuses(alertId);

    }
}