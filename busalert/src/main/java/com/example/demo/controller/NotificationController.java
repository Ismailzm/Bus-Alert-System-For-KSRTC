package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Long userId) {

        return notificationService.getNotifications(userId);
    }

    // Delete one notification
    @DeleteMapping("/{id}")
    public void deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);
    }

    // Delete all notifications of a user
    @DeleteMapping("/user/{userId}")
    public void deleteAllNotifications(
            @PathVariable Long userId) {

        notificationService.deleteAllNotifications(userId);
    }
}
