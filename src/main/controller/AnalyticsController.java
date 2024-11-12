package main.controller;

import main.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    /**
     * Endpoint to get the total number of mentor-mentee connections.
     * @return The total number of connections.
     */
    @GetMapping("/connections")
    public ResponseEntity<?> getTotalConnections() {
        int connections = analyticsService.getTotalConnections();
        return ResponseEntity.ok("Total mentor-mentee connections: " + connections);
    }

    /**
     * Endpoint to get the total number of messages exchanged.
     * @return The total number of messages exchanged.
     */
    @GetMapping("/messages")
    public ResponseEntity<?> getTotalMessages() {
        int messageCount = analyticsService.getTotalMessages();
        return ResponseEntity.ok("Total messages exchanged: " + messageCount);
    }
}
