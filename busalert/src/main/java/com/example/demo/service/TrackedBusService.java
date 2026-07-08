package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TrackedBus;
import com.example.demo.repository.TrackedBusRepository;

@Service
public class TrackedBusService {

    private final TrackedBusRepository trackedBusRepository;

    public TrackedBusService(
            TrackedBusRepository trackedBusRepository) {

        this.trackedBusRepository = trackedBusRepository;

    }

    // =====================================
    // Start Tracking
    // =====================================

    public TrackedBus startTracking(
            Long userId,
            String busNumber){

        return trackedBusRepository
                .findByUserIdAndBusNumber(userId, busNumber)
                .orElseGet(() -> {

                    TrackedBus trackedBus =
                            new TrackedBus();

                    trackedBus.setUserId(userId);

                    trackedBus.setBusNumber(busNumber);

                    return trackedBusRepository.save(trackedBus);

                });

    }

    // =====================================
    // Stop Tracking
    // =====================================

    public void stopTracking(
            Long userId,
            String busNumber){

        trackedBusRepository
                .findByUserIdAndBusNumber(userId, busNumber)
                .ifPresent(trackedBusRepository::delete);

    }

    // =====================================
    // Users Tracking a Bus
    // =====================================

    public List<TrackedBus> getTrackedUsers(
            String busNumber){

        return trackedBusRepository
                .findByBusNumber(busNumber);

    }

}