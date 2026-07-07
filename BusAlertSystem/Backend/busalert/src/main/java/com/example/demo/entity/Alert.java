package com.example.demo.entity;


import com.example.demo.enums.AlertStatus;
import com.example.demo.enums.BusType;

import jakarta.persistence.*;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String source;

    private String destination;

    @Enumerated(EnumType.STRING)
    private BusType busType;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    public Alert() {
    }

    public Alert(Long id, Long userId, String source, String destination,
                 BusType busType, AlertStatus status) {
        this.id = id;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.busType = busType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public BusType getBusType() {
        return busType;
    }

    public void setBusType(BusType busType) {
        this.busType = busType;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }
}