package main.service;

import main.entity.Notification;
import main.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Creates a new notification for a user.
     * @param userId ID of the user to receive the notification.
     * @param type Type of the notification (e.g., "MESSAGE", "MATCH").
     * @param content The content/message of the notification.
     */
    public void createNotification(Long userId, String type, String content) {
        Notification notification = new Notification(userId, type, content);
        notificationRepository.save(notification);
    }

    /**
     * Retrieves all unread notifications for a specific user.
     * @param userId ID of the user.
     * @return List of unread notifications for the user.
     */
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    /**
     * Marks a notification as read.
     * @param notificationId ID of the notification to mark as read.
     */
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    /**
     * Retrieves all notifications (read and unread) for a specific user.
     * @param userId ID of the user.
     * @return List of all notifications for the user.
     */
    public List<Notification> getAllNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
