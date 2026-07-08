package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.config.KarnatakaDistricts;
import com.example.demo.entity.Alert;
import com.example.demo.entity.Bus;
import com.example.demo.entity.BusTrip;
import com.example.demo.entity.Notification;
import com.example.demo.enums.AlertStatus;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.BusTripRepository;
import com.example.demo.repository.DelayReportRepository;
import com.example.demo.repository.NotificationRepository;

@Service
public class TripService {

    private final BusRepository busRepository;
    private final BusTripRepository busTripRepository;
    private final DelayReportRepository delayReportRepository;
    private final AlertRepository alertRepository;
    private final NotificationRepository notificationRepository;

    public TripService(
            BusRepository busRepository,
            BusTripRepository busTripRepository,
            DelayReportRepository delayReportRepository,
            AlertRepository alertRepository,
            NotificationRepository notificationRepository) {

        this.busRepository = busRepository;
        this.busTripRepository = busTripRepository;
        this.delayReportRepository = delayReportRepository;
        this.alertRepository = alertRepository;
        this.notificationRepository = notificationRepository;
    }

    // ==========================================
    // START TRIP
    // ==========================================

    public BusTrip startTrip(String busNumber) {

        // End any existing active trip
        busTripRepository.findByBusNumberAndActiveTrue(busNumber)
                .ifPresent(existingTrip -> {

                    existingTrip.setActive(false);

                    busTripRepository.save(existingTrip);

                });

        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() ->
                        new RuntimeException("Bus not found"));
     // Demo Mode

        LocalDateTime startTime = LocalDateTime.now();

        // Journey duration for demo (5 minutes)

        LocalDateTime endTime = startTime.plusMinutes(5);
        BusTrip trip = new BusTrip();

        trip.setBusNumber(bus.getBusNumber());

        trip.setSource(bus.getSource());

        trip.setDestination(bus.getDestination());

        trip.setTripStartTime(startTime);

        trip.setTripEndTime(endTime);

        trip.setActive(true);
        BusTrip savedTrip = busTripRepository.save(trip);
        
        List<Alert> activeAlerts =
                alertRepository.findByStatus(AlertStatus.ACTIVE);

        for(Alert alert : activeAlerts){

            if(alert.getSource().equalsIgnoreCase(bus.getSource())
                    &&
               alert.getDestination().equalsIgnoreCase(bus.getDestination())
                    &&
               alert.getBusType() == bus.getBusType()){

                alert.setStatus(AlertStatus.MATCHED);

                alertRepository.save(alert);

                Notification notification = new Notification();

                notification.setUserId(alert.getUserId());

                notification.setMessage(
                        "🚌 Bus "
                        + bus.getBusNumber()
                        + " has started from "
                        + bus.getSource()
                        + " to "
                        + bus.getDestination()
                        + "."
                );

                notification.setRead(false);

                notificationRepository.save(notification);

            }

        }

        return savedTrip;

    }

    // ==========================================
    // END TRIP
    // ==========================================

    public void endTrip(String busNumber) {

        BusTrip trip = busTripRepository
                .findByBusNumberAndActiveTrue(busNumber)
                .orElseThrow(() ->
                        new RuntimeException("No Active Trip Found"));

        trip.setActive(false);

        busTripRepository.save(trip);
        delayReportRepository.deleteByBusNumber(busNumber);
        

    }
    
 // ==========================================
 // REVERSE TRIP
 // ==========================================

 public BusTrip reverseTrip(String busNumber){

     // End existing active trip if present
     busTripRepository.findByBusNumberAndActiveTrue(busNumber)
             .ifPresent(existingTrip -> {

                 existingTrip.setActive(false);

                 busTripRepository.save(existingTrip);

             });

     // Find Bus
     Bus bus = busRepository.findByBusNumber(busNumber)
             .orElseThrow(() ->
                     new RuntimeException("Bus not found"));

     // Swap source and destination

     String newSource = bus.getDestination();

     String newDestination = bus.getSource();

     // Update bus route

     bus.setSource(newSource);

     bus.setDestination(newDestination);

     // Reset coordinates

     double[] coordinates =
             KarnatakaDistricts.DISTRICTS.get(newSource);

     if(coordinates != null){

         bus.setLatitude(coordinates[0]);

         bus.setLongitude(coordinates[1]);

     }

     busRepository.save(bus);

     // Create new trip

     LocalDateTime startTime = LocalDateTime.now();

     LocalDateTime endTime = startTime.plusMinutes(5);

     BusTrip trip = new BusTrip();

     trip.setBusNumber(bus.getBusNumber());

     trip.setSource(newSource);

     trip.setDestination(newDestination);

     trip.setTripStartTime(startTime);

     trip.setTripEndTime(endTime);

     trip.setActive(true);

     return busTripRepository.save(trip);

 }

}