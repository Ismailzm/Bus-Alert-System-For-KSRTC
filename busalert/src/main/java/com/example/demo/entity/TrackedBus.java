package com.example.demo.entity;



import jakarta.persistence.*;

@Entity
public class TrackedBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String busNumber;

    public TrackedBus() {
    }

    public TrackedBus(Long userId, String busNumber) {
        this.userId = userId;
        this.busNumber = busNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

}