package com.example.demo.entity;


import java.time.LocalDateTime;

import com.example.demo.enums.DelayStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DelayReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String busNumber;

    private String reason;

    private Integer delayMinutes;

    private LocalDateTime reportedAt;

    @Enumerated(EnumType.STRING)
    private DelayStatus status;

    public DelayReport() {
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

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public DelayStatus getStatus() {
        return status;
    }

    public void setStatus(DelayStatus status) {
        this.status = status;
    }

}