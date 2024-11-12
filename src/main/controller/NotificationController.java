package main.controller;

import main.entity.Notification;
import main.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Endpoint to get all unread notifications for a user.
     * @param userId ID of the user.
     * @return List of unread notifications.
     */
    @GetMapping("/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Endpoint to mark a specific notification as read.
     * @param notificationId ID of the notification to mark as read.
     * @return Success or failure response.
     */
    @PutMapping("/{notificationId}/markAsRead")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read.");
    }
}

