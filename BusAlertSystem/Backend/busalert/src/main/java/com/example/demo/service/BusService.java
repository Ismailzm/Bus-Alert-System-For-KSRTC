package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Alert;
import com.example.demo.entity.Bus;
import com.example.demo.entity.Notification;
import com.example.demo.enums.AlertStatus;
import com.example.demo.enums.BusType;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.NotificationRepository;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final AlertRepository alertRepository;
    private final NotificationRepository notificationRepository;

    public BusService(BusRepository busRepository,
                      AlertRepository alertRepository,
                      NotificationRepository notificationRepository) {

        this.busRepository = busRepository;
        this.alertRepository = alertRepository;
        this.notificationRepository = notificationRepository;
    }

    // Save Bus
    public Bus saveBus(Bus bus) {

        Bus savedBus = busRepository.save(bus);

        List<Alert> activeAlerts = alertRepository.findByStatus(AlertStatus.ACTIVE);

        for (Alert alert : activeAlerts) {

            if (alert.getSource().equalsIgnoreCase(savedBus.getSource())
                    && alert.getDestination().equalsIgnoreCase(savedBus.getDestination())
                    && alert.getBusType() == savedBus.getBusType()) {

                // Update Alert Status
                alert.setStatus(AlertStatus.MATCHED);
                alertRepository.save(alert);

                // Create Notification
                Notification notification = new Notification();

                notification.setUserId(alert.getUserId());

                notification.setMessage(
                        "Bus " + savedBus.getBusNumber()
                                + " is now available from "
                                + savedBus.getSource()
                                + " to "
                                + savedBus.getDestination()
                );

                notification.setRead(false);

                

                notificationRepository.save(notification);

                

                System.out.println("======================================");
                System.out.println("MATCH FOUND!");
                System.out.println("Alert ID : " + alert.getId());
                System.out.println("User ID  : " + alert.getUserId());
                System.out.println("Notification Created!");
                System.out.println("======================================");
            }
        }

        return savedBus;
    }

    // Get All Buses
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Get Bus By Number
    public Bus getBusByNumber(String busNumber) {
        return busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    // Update Bus
    public Bus updateBus(String busNumber, Bus updatedBus) {

        Bus existingBus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        existingBus.setBusName(updatedBus.getBusName());
        existingBus.setSource(updatedBus.getSource());
        existingBus.setDestination(updatedBus.getDestination());
        existingBus.setScheduledDeparture(updatedBus.getScheduledDeparture());
        existingBus.setScheduledArrival(updatedBus.getScheduledArrival());
        existingBus.setBusType(updatedBus.getBusType());

        return busRepository.save(existingBus);
    }

    // Delete Bus
    public void deleteBus(String busNumber) {

        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        busRepository.delete(bus);
    }

    // Search by Source & Destination
    public List<Bus> searchBuses(String source, String destination) {

        return busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);

    }

    // Search by Bus Type
    public List<Bus> getBusesByType(BusType busType) {

        return busRepository.findByBusType(busType);

    }
}