package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Save Notification
    public Notification saveNotification(Notification notification) {

        return notificationRepository.save(notification);

    }

    // Get Notifications of User
    public List<Notification> getNotifications(Long userId) {

        return notificationRepository.findByUserId(userId);

    }

}