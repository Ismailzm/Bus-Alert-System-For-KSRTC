package com.example.demo.dto;



public class DashboardResponse {

    private long totalUsers;
    private long totalBuses;
    private long activeAlerts;
    private long matchedAlerts;
    private long totalNotifications;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalUsers,
                             long totalBuses,
                             long activeAlerts,
                             long matchedAlerts,
                             long totalNotifications) {

        this.totalUsers = totalUsers;
        this.totalBuses = totalBuses;
        this.activeAlerts = activeAlerts;
        this.matchedAlerts = matchedAlerts;
        this.totalNotifications = totalNotifications;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalBuses() {
        return totalBuses;
    }

    public void setTotalBuses(long totalBuses) {
        this.totalBuses = totalBuses;
    }

    public long getActiveAlerts() {
        return activeAlerts;
    }

    public void setActiveAlerts(long activeAlerts) {
        this.activeAlerts = activeAlerts;
    }

    public long getMatchedAlerts() {
        return matchedAlerts;
    }

    public void setMatchedAlerts(long matchedAlerts) {
        this.matchedAlerts = matchedAlerts;
    }

    public long getTotalNotifications() {
        return totalNotifications;
    }

    public void setTotalNotifications(long totalNotifications) {
        this.totalNotifications = totalNotifications;
    }
}