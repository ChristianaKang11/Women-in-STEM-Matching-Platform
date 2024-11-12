package main.repository;

import main.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Finds all unread notifications for a specific user.
     * @param userId The ID of the user.
     * @return A list of unread notifications for the user.
     */
    List<Notification> findByUserIdAndIsReadFalse(Long userId);

    /**
     * Finds all notifications for a specific user, both read and unread.
     * @param userId The ID of the user.
     * @return A list of all notifications for the user.
     */
    List<Notification> findByUserId(Long userId);
}
