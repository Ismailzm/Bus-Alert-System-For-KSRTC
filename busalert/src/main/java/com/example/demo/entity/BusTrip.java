package com.example.demo.entity;



import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_trip")
public class BusTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    private String source;

    private String destination;

    private LocalDateTime tripStartTime;

    private LocalDateTime tripEndTime;

    private Boolean active;

    public BusTrip() {
    }

    public BusTrip(Long id,
                   String busNumber,
                   String source,
                   String destination,
                   LocalDateTime tripStartTime,
                   LocalDateTime tripEndTime,
                   Boolean active) {

        this.id = id;
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.tripStartTime = tripStartTime;
        this.tripEndTime = tripEndTime;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(LocalDateTime tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public LocalDateTime getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(LocalDateTime tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}