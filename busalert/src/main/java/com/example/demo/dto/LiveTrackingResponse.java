package com.example.demo.dto;

public class LiveTrackingResponse {

    private String busNumber;

    private String source;

    private String destination;

    private String currentDistrict;

    private String nextDistrict;

    private Double latitude;

    private Double longitude;

    private Integer etaMinutes;

    private String status;

    private Double progress;

    // ==========================
    // Delay Information
    // ==========================

    private Boolean delayed;

    private String delayReason;

    private Integer delayMinutes;

    public LiveTrackingResponse() {
    }

    public LiveTrackingResponse(String busNumber,
                                String currentDistrict,
                                String nextDistrict,
                                Double latitude,
                                Double longitude,
                                Integer etaMinutes,
                                String status,
                                Double progress) {

        this.busNumber = busNumber;
        this.currentDistrict = currentDistrict;
        this.nextDistrict = nextDistrict;
        this.latitude = latitude;
        this.longitude = longitude;
        this.etaMinutes = etaMinutes;
        this.status = status;
        this.progress = progress;
    }

    // ==========================
    // Bus Number
    // ==========================

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    // ==========================
    // Source
    // ==========================

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    // ==========================
    // Destination
    // ==========================

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    // ==========================
    // Current District
    // ==========================

    public String getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(String currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    // ==========================
    // Next District
    // ==========================

    public String getNextDistrict() {
        return nextDistrict;
    }

    public void setNextDistrict(String nextDistrict) {
        this.nextDistrict = nextDistrict;
    }

    // ==========================
    // Latitude
    // ==========================

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    // ==========================
    // Longitude
    // ==========================

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    // ==========================
    // ETA
    // ==========================

    public Integer getEtaMinutes() {
        return etaMinutes;
    }

    public void setEtaMinutes(Integer etaMinutes) {
        this.etaMinutes = etaMinutes;
    }

    // ==========================
    // Status
    // ==========================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ==========================
    // Progress
    // ==========================

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    // ==========================
    // Delay Status
    // ==========================

    public Boolean getDelayed() {
        return delayed;
    }

    public void setDelayed(Boolean delayed) {
        this.delayed = delayed;
    }

    // ==========================
    // Delay Reason
    // ==========================

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    // ==========================
    // Delay Minutes
    // ==========================

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

}