package com.example.demo.service;



import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.enums.AlertStatus;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final AlertRepository alertRepository;
    private final NotificationRepository notificationRepository;

    public DashboardService(UserRepository userRepository,
                            BusRepository busRepository,
                            AlertRepository alertRepository,
                            NotificationRepository notificationRepository) {

        this.userRepository = userRepository;
        this.busRepository = busRepository;
        this.alertRepository = alertRepository;
        this.notificationRepository = notificationRepository;
    }

    public DashboardResponse getDashboardData() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalUsers(userRepository.count());

        response.setTotalBuses(busRepository.count());

        response.setActiveAlerts(
                alertRepository.countByStatus(AlertStatus.ACTIVE));

        response.setMatchedAlerts(
                alertRepository.countByStatus(AlertStatus.MATCHED));

        response.setTotalNotifications(
                notificationRepository.count());

        return response;
    }
}
