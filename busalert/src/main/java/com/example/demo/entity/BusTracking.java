package com.example.demo.entity;



import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_tracking")
public class BusTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String busNumber;

    private String currentDistrict;

    private Double latitude;

    private Double longitude;

    private Integer currentRouteIndex;

    private Boolean trackingEnabled;

    private LocalDateTime lastUpdated;

    public BusTracking() {
    }

    public BusTracking(Long id,
                       String busNumber,
                       String currentDistrict,
                       Double latitude,
                       Double longitude,
                       Integer currentRouteIndex,
                       Boolean trackingEnabled,
                       LocalDateTime lastUpdated) {

        this.id = id;
        this.busNumber = busNumber;
        this.currentDistrict = currentDistrict;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentRouteIndex = currentRouteIndex;
        this.trackingEnabled = trackingEnabled;
        this.lastUpdated = lastUpdated;
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

    public String getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(String currentDistrict) {
        this.currentDistrict = currentDistrict;
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

    public Integer getCurrentRouteIndex() {
        return currentRouteIndex;
    }

    public void setCurrentRouteIndex(Integer currentRouteIndex) {
        this.currentRouteIndex = currentRouteIndex;
    }

    public Boolean getTrackingEnabled() {
        return trackingEnabled;
    }

    public void setTrackingEnabled(Boolean trackingEnabled) {
        this.trackingEnabled = trackingEnabled;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}