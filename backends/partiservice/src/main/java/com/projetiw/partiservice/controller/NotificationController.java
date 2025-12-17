package com.projetiw.partiservice.controller;

import com.projetiw.partiservice.model.Notification;
import com.projetiw.partiservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PutMapping("/read/{id}")
    public void markNotificationRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }
}
