package main.service;

import main.repository.MessageRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Calculates the total number of mentor-mentee connections.
     * Assumes a "mentor-mentee" relationship is based on distinct role pairs.
     * @return The total number of unique mentor-mentee connections.
     */
    public int getTotalConnections() {
        // Placeholder for actual connection logic
        return (int) userRepository.count();
    }

    /**
     * Retrieves the total count of messages exchanged.
     * @return The total number of messages.
     */
    public int getTotalMessages() {
        return (int) messageRepository.count();
    }
}