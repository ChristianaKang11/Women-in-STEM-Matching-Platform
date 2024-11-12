package main.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String type; // e.g., "MESSAGE", "MATCH"

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private boolean isRead;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Default constructor for JPA
    public Notification() {}

    /**
     * Constructor for creating a new Notification.
     * @param userId The ID of the user who receives the notification.
     * @param type The type of notification (e.g., "MESSAGE", "MATCH").
     * @param content The content or message of the notification.
     */
    public Notification(Long userId, String type, String content) {
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.isRead = false; // New notifications are unread by default
        this.timestamp = LocalDateTime.now(); // Timestamp set to current time
    }

    // Getters and Setters for each field

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

