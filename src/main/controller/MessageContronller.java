package main.controller;

import main.entity.Message;
import main.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessagingService messagingService;

    /**
     * Sends a new message from one user to another.
     * @param senderId ID of the sender user.
     * @param receiverId ID of the receiver user.
     * @param content Content of the message.
     * @return The sent message.
     */
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        Message message = messagingService.sendMessage(senderId, receiverId, content);
        return ResponseEntity.ok(message);
    }

    /**
     * Retrieves the conversation history between two users.
     * @param senderId ID of one of the users.
     * @param receiverId ID of the other user.
     * @return List of messages exchanged between the two users.
     */
    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(@RequestParam Long senderId, @RequestParam Long receiverId) {
        List<Message> conversation = messagingService.getConversation(senderId, receiverId);
        return ResponseEntity.ok(conversation);
    }

    /**
     * Retrieves the latest message in a conversation between two users.
     * @param senderId ID of one of the users.
     * @param receiverId ID of the other user.
     * @return The latest message.
     */
    @GetMapping("/latest")
    public ResponseEntity<?> getLatestMessage(@RequestParam Long senderId, @RequestParam Long receiverId) {
        Message latestMessage = messagingService.getLatestMessage(senderId, receiverId);
        if (latestMessage != null) {
            return ResponseEntity.ok(latestMessage);
        } else {
            return ResponseEntity.status(404).body("No messages found between these users.");
        }
    }
}
