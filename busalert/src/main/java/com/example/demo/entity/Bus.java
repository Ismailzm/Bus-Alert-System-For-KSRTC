package com.example.demo.entity;

import java.time.LocalTime;

import com.example.demo.enums.BusType;

import jakarta.persistence.*; 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bus")
public class Bus {


    @Column
private Double latitude;

@Column
private Double longitude;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String busNumber;

    @NotBlank
    @Column(nullable = false)
    private String busName;

    @NotBlank
    @Column(nullable = false)
    private String source;

    @NotBlank
    @Column(nullable = false)
    private String destination;

    @NotNull
    private LocalTime scheduledDeparture;

    @NotNull
    private LocalTime scheduledArrival;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BusType busType;

    // No-Argument Constructor
    public Bus() {
    }

    // All-Argument Constructor
   public Bus(Long id,
           String busNumber,
           String busName,
           String source,
           String destination,
           LocalTime scheduledDeparture,
           LocalTime scheduledArrival,
           BusType busType,
           Double latitude,
           Double longitude) {

    this.id = id;
    this.busNumber = busNumber;
    this.busName = busName;
    this.source = source;
    this.destination = destination;
    this.scheduledDeparture = scheduledDeparture;
    this.scheduledArrival = scheduledArrival;
    this.busType = busType;
    this.latitude = latitude;
    this.longitude = longitude;
}

    // Getters and Setters

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

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
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

    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public BusType getBusType() {
        return busType;
    }

    public void setBusType(BusType busType) {
        this.busType = busType;
    }

    public Double getLatitude() {
    return latitude;
}

public void setLatitude(Double latitude) {
    this.latitude = latitude;
}

public Double getLongitude() {
    return longitude;
}

public void setLongitude(Double longitude) {
    this.longitude = longitude;
}
}
